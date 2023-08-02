package ziyad.com.ecommercerestapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ziyad.com.ecommercerestapi.entity.Order;
import ziyad.com.ecommercerestapi.entity.OrderItem;
import ziyad.com.ecommercerestapi.entity.Product;
import ziyad.com.ecommercerestapi.entity.User;
import ziyad.com.ecommercerestapi.exception.ProductApiException;
import ziyad.com.ecommercerestapi.payload.OrderDto;
import ziyad.com.ecommercerestapi.payload.OrderItemDto;
import ziyad.com.ecommercerestapi.repository.OrderItemRepository;
import ziyad.com.ecommercerestapi.repository.OrderRepository;
import ziyad.com.ecommercerestapi.repository.ProductRepository;
import ziyad.com.ecommercerestapi.repository.UserRepository;
import ziyad.com.ecommercerestapi.service.OrderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
@Service

public class OrderServiceImpl implements OrderService {
    @Override
    public Order createOrder(OrderDto orderDto) {
        return null;
    }
}
