package ziyad.com.ecommercerestapi.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ziyad.com.ecommercerestapi.payload.AddressDto;
import ziyad.com.ecommercerestapi.payload.ResponseAddressDto;
import ziyad.com.ecommercerestapi.service.AdressService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {
    private AdressService adressService;

    public AddressController(AdressService adressService) {
        this.adressService = adressService;
    }

    @Operation(
            summary = "Create an address",
            description = "Create an address for a user."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Address created successfully"
    )
    @PostMapping()
    public ResponseEntity<ResponseAddressDto> createAddress(@RequestBody AddressDto addressDto) {
        return new ResponseEntity<>(adressService.createAddress(addressDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get an address by ID",
            description = "Get an address by its ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Address found"
    )
    @GetMapping("{id}")
    public ResponseEntity<ResponseAddressDto> getAddressById(@PathVariable("id") Long addressId) {
        return ResponseEntity.ok(adressService.getAddressById(addressId));
    }

    @Operation(
            summary = "Get all addresses for a user",
            description = "Get all addresses for a user by their ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Addresses found"
    )
    @GetMapping("user/{id}")
    public ResponseEntity<List<ResponseAddressDto>> getUserAddresses(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(adressService.getUserAddresses(userId));
    }

    @Operation(
            summary = "Update an address",
            description = "Update an address by its ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Address updated successfully"
    )
    @PutMapping("{id}")
    public ResponseEntity<ResponseAddressDto> updateAddress(
            @PathVariable("id") Long addressId,
            @RequestBody AddressDto addressDto
    ) {
        return ResponseEntity.ok(adressService.updateAddress(addressId, addressDto));
    }

    @Operation(
            summary = "Delete an address",
            description = "Delete an address by its ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Address deleted successfully"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable("id") Long addressId) {
        adressService.deleteAddress(addressId);
        return ResponseEntity.ok("Address deleted successfully");
    }
}
