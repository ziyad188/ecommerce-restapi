package ziyad.com.ecommercerestapi.service;

import ziyad.com.ecommercerestapi.payload.*;

import java.util.List;

public interface OrderService {
    ResponseOrderDto placeOrder(Long userId, Long addressId, PaymentRequestDto pymentRequestDto);
    List<ResponseOrderDto> getUserAllOrder(Long userId);
    ResponseOrderDto getOrderById(Long orderId);
    List<ResponseOrderDto> getAllOrder();
    UpdateStatusDto updateStatus(Long orderId, UpdateStatusDto status);
    void cancelOrder(Long orderId);



}
