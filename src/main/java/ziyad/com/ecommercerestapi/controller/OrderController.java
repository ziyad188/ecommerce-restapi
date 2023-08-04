package ziyad.com.ecommercerestapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ziyad.com.ecommercerestapi.payload.PaymentRequestDto;
import ziyad.com.ecommercerestapi.payload.ResponseOrderDto;
import ziyad.com.ecommercerestapi.payload.UpdateStatusDto;
import ziyad.com.ecommercerestapi.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //place order
    @PostMapping("{id}")
    public ResponseEntity<ResponseOrderDto> placeOrder(@PathVariable("id") Long userId, @RequestParam Long aid, @RequestBody PaymentRequestDto pymentRequestDto){
        return new ResponseEntity<>(orderService.placeOrder(userId,aid,pymentRequestDto), HttpStatus.CREATED);
    }

    //get all order
    @GetMapping("user/{id}")
    public ResponseEntity<List<ResponseOrderDto>> getAllOrderByUserId(@PathVariable("id") Long userId){
        return ResponseEntity.ok(orderService.getUserAllOrder(userId));
    }
    //get order by specific id
    @GetMapping("{id}")
    public ResponseEntity<ResponseOrderDto> getOrderById(@PathVariable("id") Long orderId){
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    //get all orders
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ResponseOrderDto>> getAllOrder(){
        return ResponseEntity.ok(orderService.getAllOrder());
    }
    //update status
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<UpdateStatusDto> updateStatus(@PathVariable("id")Long orderId, @RequestBody UpdateStatusDto status){
        return ResponseEntity.ok(orderService.updateStatus(orderId,status));
    }
    //cancel order
    @DeleteMapping("{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable("id")Long orderId){
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("Canceled order");
    }
}
