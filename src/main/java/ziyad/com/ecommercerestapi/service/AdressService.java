package ziyad.com.ecommercerestapi.service;

import ziyad.com.ecommercerestapi.entity.Address;
import ziyad.com.ecommercerestapi.payload.AddressDto;
import ziyad.com.ecommercerestapi.payload.ResponseAddressDto;

import java.util.List;

public interface AdressService {
    ResponseAddressDto createAddress(AddressDto addressDto);
    ResponseAddressDto getAddressById(Long addressId);
    List<ResponseAddressDto> getUserAddresses(Long userId);
    ResponseAddressDto updateAddress(Long addressId, AddressDto addressDto);
    void deleteAddress(Long addressId);

}
