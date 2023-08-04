package ziyad.com.ecommercerestapi.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(
            summary = "Place an order",
            description = "Place a new order for a user."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Order placed successfully"
    )
    @PostMapping("{id}")
    public ResponseEntity<ResponseOrderDto> placeOrder(
            @PathVariable("id") Long userId,
            @RequestParam Long aid,
            @RequestBody PaymentRequestDto paymentRequestDto
    ) {
        return new ResponseEntity<>(orderService.placeOrder(userId, aid, paymentRequestDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get all orders for a user",
            description = "Get all orders for a specific user by their ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Orders found"
    )
    @GetMapping("user/{id}")
    public ResponseEntity<List<ResponseOrderDto>> getAllOrderByUserId(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(orderService.getUserAllOrder(userId));
    }

    @Operation(
            summary = "Get an order by ID",
            description = "Get an order by its ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Order found"
    )
    @GetMapping("{id}")
    public ResponseEntity<ResponseOrderDto> getOrderById(@PathVariable("id") Long orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @Operation(
            summary = "Get all orders",
            description = "Get all orders."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Orders found"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ResponseOrderDto>> getAllOrder() {
        return ResponseEntity.ok(orderService.getAllOrder());
    }

    @Operation(
            summary = "Update order status",
            description = "Update the status of an order by its ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Order status updated successfully"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<UpdateStatusDto> updateStatus(
            @PathVariable("id") Long orderId,
            @RequestBody UpdateStatusDto status
    ) {
        return ResponseEntity.ok(orderService.updateStatus(orderId, status));
    }

    @Operation(
            summary = "Cancel an order",
            description = "Cancel an order by its ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Order canceled successfully"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable("id") Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("Canceled order");
    }
}
