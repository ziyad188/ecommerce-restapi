package ziyad.com.ecommercerestapi.controller;

import lombok.Getter;
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

    //create address
    @PostMapping()
    public ResponseEntity<ResponseAddressDto> createAddress(@RequestBody AddressDto addressDto){
        return new ResponseEntity<>(adressService.createAddress(addressDto), HttpStatus.CREATED);
    }
//get address
    @GetMapping("{id}")
    public ResponseEntity<ResponseAddressDto> getAddressById(@PathVariable("id") Long addressId){
        return ResponseEntity.ok(adressService.getAddressById(addressId));
    }
    //get user all adress

    @GetMapping("user/{id}")
    public ResponseEntity<List<ResponseAddressDto>> getUserAddresses(@PathVariable("id") Long userId){
        return ResponseEntity.ok(adressService.getUserAddresses(userId));
    }
    //update address
@PutMapping("{id}")
    public ResponseEntity<ResponseAddressDto> updateAddress(@PathVariable("id") Long addressId, @RequestBody AddressDto addressDto){
        return ResponseEntity.ok(adressService.updateAddress(addressId,addressDto));
    }
    //delete adress
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable("id")Long addressId){
        adressService.deleteAddress(addressId);
        return ResponseEntity.ok("Address deleted successfully");
    }
}
