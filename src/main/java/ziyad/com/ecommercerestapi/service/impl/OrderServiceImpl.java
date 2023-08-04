package ziyad.com.ecommercerestapi.service.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.modelmapper.ModelMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ziyad.com.ecommercerestapi.entity.*;
import ziyad.com.ecommercerestapi.exception.PaymentException;
import ziyad.com.ecommercerestapi.exception.ProductApiException;
import ziyad.com.ecommercerestapi.exception.ResourceNotFoundException;
import ziyad.com.ecommercerestapi.payload.*;
import ziyad.com.ecommercerestapi.repository.*;
import ziyad.com.ecommercerestapi.service.CartService;
import ziyad.com.ecommercerestapi.service.OrderService;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
@Service

public class OrderServiceImpl implements OrderService {
    private CartService cartService;
    private CartRepository cartRepository;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private OrderItemRepository orderItemRepository;
    private ModelMapper mapper;
    private PaymentRepository paymentRepository;


    public OrderServiceImpl(CartService cartService, CartRepository cartRepository, OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository, OrderItemRepository orderItemRepository, ModelMapper mapper, PaymentRepository paymentRepository) {
        this.cartService = cartService;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderItemRepository = orderItemRepository;
        this.mapper = mapper;
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public ResponseOrderDto placeOrder(Long userId, Long addressId, PaymentRequestDto pymentRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        List<Cart> carts = cartRepository.findByUser(user);
        BigDecimal totalPrice = cartService.calculateTotalPrice(userId);
         final String STRIPE_SECRET_KEY ="sk_test_51NbPtWSCBQF2Mfr90qS1FivP6oDesP34n1198v3RW5cubiqKDhmAp1U0dPlw8wQF3zyiiw9MFvzZE7lyinJooykG004ADESXpH";
        //payment
        try {
            Stripe.apiKey = STRIPE_SECRET_KEY;
            Map<String, Object> params = new HashMap<>();
            params.put("amount", totalPrice.multiply(new BigDecimal(100)).intValue()); // Stripe expects the amount in cents
            params.put("currency", "USD"); // Use the correct currency code
            String[] paymentMethodTypes = new String[]{"card"};
            params.put("payment_method_types", paymentMethodTypes); // Allow only card payments for now

            PaymentIntent paymentIntent = PaymentIntent.create(params);
            String intentId = paymentIntent.getId();

            String apiUrl = "https://api.stripe.com/v1/payment_intents/" + intentId + "/confirm";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + STRIPE_SECRET_KEY);
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
            requestBody.add("payment_method_types[]", "card"); // This line may not be needed anymore
            requestBody.add("payment_method_data[type]", pymentRequestDto.getType());
            requestBody.add("payment_method_data[card][token]", pymentRequestDto.getData());
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            // Step 4: Handle the response (you may want to do something with the response)
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Payment Intent confirmed successfully.");
                System.out.println(response.getBody());
            } else {
                System.out.println("Failed to confirm Payment Intent.");
                System.out.println("Status code: " + response.getStatusCode());
                System.out.println("Response body: " + response.getBody());
            }
        } catch (StripeException e) {
            throw new RuntimeException("Error creating or confirming payment intent: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred during the payment process: " + e.getMessage());
        }

        List<OrderItem> orderItems = carts.stream()
                .map(cart -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setSubtotal(cart.getProduct().getUnitPrice());
                    orderItem.setProduct(cart.getProduct());
                    orderItem.setQuantity(cart.getQuantity());
                    return orderItem;
                })
                .collect(Collectors.toList());

        // Save order items in the orderItemRepository
        List<OrderItem> savedItems = orderItemRepository.saveAll(orderItems);

        cartService.clearUserCart(userId);

        Order order = new Order();
        order.setOrderStatus("PLACED");

        order.setOrderItems(orderItems);
        order.setUser(user);

        order.setTotalPrice(totalPrice);
        order.setBillingAddress(user.getAddress().stream().filter(address -> address.getId().equals(addressId)).findFirst().orElse(null));
        Order savedOrder = orderRepository.save(order);

        //save order to order item
        List<OrderItem> itemsm = savedItems.stream()
                .map(item->{
                    item.setOrder(savedOrder);
                    return item;
                        }).collect(Collectors.toList());
        //payment if success
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentAmount(totalPrice);
        payment.setPaymentStatus("SUCCESS");
        paymentRepository.save(payment);

        ResponseOrderDto responseOrderDto = new ResponseOrderDto();
        responseOrderDto.setOrderDate(savedOrder.getOrderDate());
        responseOrderDto.setOrderStatus(savedOrder.getOrderStatus());
        responseOrderDto.setId(savedOrder.getId());
        responseOrderDto.setUserId(savedOrder.getUser().getId());
        responseOrderDto.setTotalPrice(savedOrder.getTotalPrice());

        AddressDto addressDto = new AddressDto();
        addressDto.setId(savedOrder.getBillingAddress().getId());
        addressDto.setUserId(savedOrder.getBillingAddress().getId());
        addressDto.setStreetAddress(savedOrder.getBillingAddress().getStreetAddress());
        addressDto.setCity(savedOrder.getBillingAddress().getCity());
        addressDto.setState(savedOrder.getBillingAddress().getState());
        addressDto.setZipCode(savedOrder.getBillingAddress().getZipCode());

        responseOrderDto.setBillingAddress(addressDto);

        List<OrderItemDto> items = savedItems.stream()
                .map(item -> {
                    OrderItemDto orderItemDto = new OrderItemDto();
                    orderItemDto.setQuantity(item.getQuantity());
                    orderItemDto.setProductName(item.getProduct().getName());
                    orderItemDto.setProductId(item.getProduct().getId());
                    return orderItemDto;
                })
                .collect(Collectors.toList());
        responseOrderDto.setOrderItems(items);

        return responseOrderDto;
    }
//get all orders of user
    @Override
    public List<ResponseOrderDto> getUserAllOrder(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        List<Order> orders = orderRepository.findAllByUserIdOrderByOrderDateDesc(userId);


        List<ResponseOrderDto> responseOrder = orders.stream()
                .map(order -> {
                    ResponseOrderDto responseOrderDto = new ResponseOrderDto();

                    List<OrderItemDto> items = order.getOrderItems().stream()
                                    .map(orderItem ->{
                                        OrderItemDto orderItemDto = new OrderItemDto();
                                        orderItemDto.setQuantity(orderItem.getQuantity());
                                        orderItemDto.setProductName(orderItem.getProduct().getName());
                                        orderItemDto.setProductId(orderItem.getProduct().getId());
                                        return orderItemDto;
                                    }).collect(Collectors.toList());
                    responseOrderDto.setOrderItems(items);
                    responseOrderDto.setOrderDate(order.getOrderDate());
                    responseOrderDto.setOrderStatus(order.getOrderStatus());
                    responseOrderDto.setId(order.getId());
                    responseOrderDto.setUserId(order.getUser().getId());
                    responseOrderDto.setTotalPrice(order.getTotalPrice());
                    AddressDto addressDto = new AddressDto();
                    addressDto.setId(order.getBillingAddress().getId());
                    addressDto.setUserId(order.getBillingAddress().getId());
                    addressDto.setStreetAddress(order.getBillingAddress().getStreetAddress());
                    addressDto.setCity(order.getBillingAddress().getCity());
                    addressDto.setState(order.getBillingAddress().getState());
                    addressDto.setZipCode(order.getBillingAddress().getZipCode());
                    responseOrderDto.setBillingAddress(addressDto);
                    return responseOrderDto;

                }).collect(Collectors.toList());

        return responseOrder;
    }
    //get all orderBy id

    @Override
    public ResponseOrderDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new ResourceNotFoundException("Order","Id",orderId));
        ResponseOrderDto responseOrderDto = new ResponseOrderDto();
        responseOrderDto.setOrderStatus(order.getOrderStatus());
        responseOrderDto.setId(orderId);
        responseOrderDto.setOrderDate(order.getOrderDate());
        responseOrderDto.setTotalPrice(order.getTotalPrice());
        responseOrderDto.setUserId(order.getUser().getId());
        //orderitem map
        List<OrderItemDto> orderItemDtos = order.getOrderItems().stream()
                        .map(orderItem -> {
                            OrderItemDto orderItemDto = new OrderItemDto();
                            orderItemDto.setQuantity(orderItem.getQuantity());
                            orderItemDto.setProductName(orderItem.getProduct().getName());
                            orderItemDto.setProductId(orderItem.getProduct().getId());
                            return orderItemDto;
                        }).collect(Collectors.toList());
        responseOrderDto.setOrderItems(orderItemDtos);
        //address maping
        AddressDto addressDto = new AddressDto();
        addressDto.setId(order.getBillingAddress().getId());
        addressDto.setUserId(order.getBillingAddress().getId());
        addressDto.setStreetAddress(order.getBillingAddress().getStreetAddress());
        addressDto.setCity(order.getBillingAddress().getCity());
        addressDto.setState(order.getBillingAddress().getState());
        addressDto.setZipCode(order.getBillingAddress().getZipCode());
        responseOrderDto.setBillingAddress(addressDto);
        return responseOrderDto;
    }
//get all orders
    @Override
    public List<ResponseOrderDto> getAllOrder() {
        List<Order> orders = orderRepository.findAll();
        List<ResponseOrderDto> responseOrderDtos = orders.stream()
                .map(order -> {
                    ResponseOrderDto responseOrderDto = new ResponseOrderDto();

                    List<OrderItemDto> items = order.getOrderItems().stream()
                            .map(orderItem ->{
                                OrderItemDto orderItemDto = new OrderItemDto();
                                orderItemDto.setQuantity(orderItem.getQuantity());
                                orderItemDto.setProductName(orderItem.getProduct().getName());
                                orderItemDto.setProductId(orderItem.getProduct().getId());
                                return orderItemDto;
                            }).collect(Collectors.toList());
                    responseOrderDto.setOrderItems(items);
                    responseOrderDto.setOrderDate(order.getOrderDate());
                    responseOrderDto.setOrderStatus(order.getOrderStatus());
                    responseOrderDto.setId(order.getId());
                    responseOrderDto.setUserId(order.getUser().getId());
                    responseOrderDto.setTotalPrice(order.getTotalPrice());
                    AddressDto addressDto = new AddressDto();
                    addressDto.setId(order.getBillingAddress().getId());
                    addressDto.setUserId(order.getBillingAddress().getId());
                    addressDto.setStreetAddress(order.getBillingAddress().getStreetAddress());
                    addressDto.setCity(order.getBillingAddress().getCity());
                    addressDto.setState(order.getBillingAddress().getState());
                    addressDto.setZipCode(order.getBillingAddress().getZipCode());
                    responseOrderDto.setBillingAddress(addressDto);
                    return responseOrderDto;

                }).collect(Collectors.toList());
        return responseOrderDtos;
    }
    //update order status
    @Override
    public UpdateStatusDto updateStatus(Long orderId, UpdateStatusDto status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new ResourceNotFoundException("Order","Id",orderId));
        order.setOrderStatus(status.getStatus());
        order.setOrderDate(order.getOrderDate());
        order.setOrderItems(order.getOrderItems());
        order.setId(orderId);
        order.setUser(order.getUser());
        order.setBillingAddress(order.getBillingAddress());
        order.setTotalPrice(order.getTotalPrice());
        Order savedOrder = orderRepository.save(order);
        UpdateStatusDto updateStatusDto = new UpdateStatusDto();
        updateStatusDto.setStatus(savedOrder.getOrderStatus());
        return updateStatusDto;
    }
//cancel order
    @Override
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new ResourceNotFoundException("Order","Id",orderId));
        orderRepository.delete(order);
    }
}
