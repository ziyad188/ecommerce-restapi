package ziyad.com.ecommercerestapi.service;

import ziyad.com.ecommercerestapi.entity.Order;
import ziyad.com.ecommercerestapi.entity.OrderItem;
import ziyad.com.ecommercerestapi.entity.User;
import ziyad.com.ecommercerestapi.payload.OrderDto;
import ziyad.com.ecommercerestapi.payload.OrderItemDto;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderDto orderDto);

}
