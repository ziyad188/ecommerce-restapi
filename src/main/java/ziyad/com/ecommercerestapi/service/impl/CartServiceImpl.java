package ziyad.com.ecommercerestapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import ziyad.com.ecommercerestapi.entity.Cart;
import ziyad.com.ecommercerestapi.entity.Product;
import ziyad.com.ecommercerestapi.entity.User;
import ziyad.com.ecommercerestapi.exception.ResourceNotFoundException;
import ziyad.com.ecommercerestapi.payload.ResponseAddressDto;
import ziyad.com.ecommercerestapi.payload.ResponseCartDto;
import ziyad.com.ecommercerestapi.repository.CartRepository;
import ziyad.com.ecommercerestapi.repository.ProductRepository;
import ziyad.com.ecommercerestapi.repository.UserRepository;
import ziyad.com.ecommercerestapi.service.CartService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private CartRepository cartRepository;
    private ModelMapper mapper;

    public CartServiceImpl(UserRepository userRepository, ProductRepository productRepository, CartRepository cartRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.mapper = mapper;
    }
//add cart
    @Override
    public ResponseCartDto addCart(Long userId, Long productId, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product","id",productId));
        Cart existingCart = cartRepository.findByUserAndProduct(user,product);
        if(existingCart!=null){
            existingCart.setQuantity(existingCart.getQuantity()+quantity);
               cartRepository.save(existingCart);
               //response Cart
            ResponseCartDto responseCartDto = new ResponseCartDto();
            responseCartDto.setCartId(existingCart.getId());
            responseCartDto.setQuantity(existingCart.getQuantity());
            responseCartDto.setProductName(existingCart.getProduct().getName());
            responseCartDto.setProductDescription(existingCart.getProduct().getDescription());
            responseCartDto.setProductId(existingCart.getProduct().getId());
            responseCartDto.setUserId(existingCart.getUser().getId());
            responseCartDto.setProductUnitPrice(existingCart.getProduct().getUnitPrice());
            responseCartDto.setProductImageUrl(existingCart.getProduct().getImageUrl());
            return responseCartDto;
        }else{
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setProduct(product);
            cart.setQuantity(quantity);
            Cart savedCart = cartRepository.save(cart);
            //response cart
            ResponseCartDto responseCartDto = new ResponseCartDto();
            responseCartDto.setCartId(savedCart.getId());
            responseCartDto.setQuantity(savedCart.getQuantity());
            responseCartDto.setProductName(savedCart.getProduct().getName());
            responseCartDto.setProductDescription(savedCart.getProduct().getDescription());
            responseCartDto.setProductId(savedCart.getProduct().getId());
            responseCartDto.setUserId(savedCart.getUser().getId());
            responseCartDto.setProductUnitPrice(savedCart.getProduct().getUnitPrice());
            responseCartDto.setProductImageUrl(savedCart.getProduct().getImageUrl());
            return responseCartDto;
        }
    }
    public List<ResponseCartDto> getUserCarts(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        List<Cart> carts = cartRepository.findByUser(user);

        List<ResponseCartDto> responseCartDtos = carts.stream()
                .map(cart -> {
                    ResponseCartDto responseCartDto = new ResponseCartDto();
                    responseCartDto.setCartId(cart.getId());
                    responseCartDto.setQuantity(cart.getQuantity());
                    responseCartDto.setProductName(cart.getProduct().getName());
                    responseCartDto.setProductDescription(cart.getProduct().getDescription());
                    responseCartDto.setProductId(cart.getProduct().getId());
                    responseCartDto.setUserId(cart.getUser().getId());
                    responseCartDto.setProductUnitPrice(cart.getProduct().getUnitPrice());
                    responseCartDto.setProductImageUrl(cart.getProduct().getImageUrl());
                    return responseCartDto;
                })
                .collect(Collectors.toList());

        return responseCartDtos;
    }

//update cart qty
    @Override
    public ResponseCartDto updateCartQuntity(Long cartId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(()->new ResourceNotFoundException("Cart","id",cartId));
        cart.setQuantity(quantity);
        Cart updateCart = cartRepository.save(cart);
        //response
        ResponseCartDto responseCartDto = new ResponseCartDto();
        responseCartDto.setCartId(updateCart.getId());
        responseCartDto.setUserId(updateCart.getUser().getId());
        responseCartDto.setProductUnitPrice(updateCart.getProduct().getUnitPrice());
        responseCartDto.setProductId(updateCart.getProduct().getId());
        responseCartDto.setProductName(updateCart.getProduct().getName());
        responseCartDto.setProductDescription(updateCart.getProduct().getDescription());
        responseCartDto.setQuantity(updateCart.getQuantity());
        responseCartDto.setProductImageUrl(updateCart.getProduct().getImageUrl());
        return responseCartDto;
    }
//remove cart item
    @Override
    public void removeCartItem(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(()->new ResourceNotFoundException("Cart","id",cartId));
        cartRepository.deleteById(cartId);
    }

    @Transactional
    @Override
    public void clearUserCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        cartRepository.deleteByUser(user);
    }


    @Override
    public double calculateTotalPrice(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        List<Cart> carts = cartRepository.findByUser(user);

        return carts.stream()
                .map(cart -> cart.getQuantity()*cart.getProduct().getUnitPrice().doubleValue())
                .reduce(0.0,Double::sum);
    }
}
