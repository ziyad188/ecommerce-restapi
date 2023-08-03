package ziyad.com.ecommercerestapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;
import ziyad.com.ecommercerestapi.entity.Address;
import ziyad.com.ecommercerestapi.entity.User;
import ziyad.com.ecommercerestapi.exception.ResourceNotFoundException;
import ziyad.com.ecommercerestapi.payload.AddressDto;
import ziyad.com.ecommercerestapi.payload.ResponseAddressDto;
import ziyad.com.ecommercerestapi.repository.AddressRepository;
import ziyad.com.ecommercerestapi.repository.UserRepository;
import ziyad.com.ecommercerestapi.service.AdressService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AdressService {
    private AddressRepository addressRepository;
    private UserRepository userRepository;
    private ModelMapper mapper;

    public AddressServiceImpl(AddressRepository addressRepository, UserRepository userRepository, ModelMapper mapper) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public ResponseAddressDto createAddress(AddressDto addressDto) {
        User user = userRepository.findById(addressDto.getUserId())
                .orElseThrow(()->new ResourceNotFoundException("User","id",addressDto.getUserId()));
        if (user == null) {
            throw new IllegalArgumentException("User with the provided ID not found.");
        }
        Address address = new Address();
        address.setUser(user);
        address.setCity(addressDto.getCity());
        address.setStreetAddress(addressDto.getStreetAddress());
        address.setState(addressDto.getState());
        address.setZipCode(addressDto.getZipCode());
        Address savedAddress =addressRepository.save(address);
        //create response
        ResponseAddressDto responseAddressDto = new ResponseAddressDto();
        responseAddressDto.setCity(savedAddress.getCity());
        responseAddressDto.setStreetAddress(savedAddress.getStreetAddress());
        responseAddressDto.setId(savedAddress.getId());
        responseAddressDto.setState(savedAddress.getState());
        responseAddressDto.setZipCode(savedAddress.getZipCode());
        responseAddressDto.setUserId(savedAddress.getUser().getId());
        return responseAddressDto;
    }

    @Override
    public ResponseAddressDto getAddressById(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(()-> new ResourceNotFoundException("Address","id",addressId));
        //creating response
        ResponseAddressDto responseAddressDto = new ResponseAddressDto();
        responseAddressDto.setCity(address.getCity());
        responseAddressDto.setStreetAddress(address.getStreetAddress());
        responseAddressDto.setId(address.getId());
        responseAddressDto.setState(address.getState());
        responseAddressDto.setZipCode(address.getZipCode());
        responseAddressDto.setUserId(address.getUser().getId());
        return responseAddressDto;
    }

    @Override
    public List<ResponseAddressDto> getUserAddresses(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        List<Address> addresses = addressRepository.findByUserId(userId);
        ResponseAddressDto responseAddressDto = new ResponseAddressDto();
        List<ResponseAddressDto> responseAddressDtos = addresses.stream()
                .map(address -> {
                    responseAddressDto.setUserId(address.getUser().getId());
                    responseAddressDto.setCity(address.getCity());
                    responseAddressDto.setStreetAddress(address.getStreetAddress());
                    responseAddressDto.setState(address.getState());
                    responseAddressDto.setZipCode(address.getZipCode());
                    responseAddressDto.setId(address.getId());
                    return responseAddressDto;
                }).collect(Collectors.toList());
        return responseAddressDtos;
    }

    @Override
    public ResponseAddressDto updateAddress(Long addressId, AddressDto addressDto) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(()-> new ResourceNotFoundException("Address","id",addressId));
        address.setId(addressId);
        address.setUser(address.getUser());
        address.setCity(addressDto.getCity());
        address.setStreetAddress(addressDto.getStreetAddress());
        address.setState(addressDto.getState());
        address.setZipCode(addressDto.getZipCode());
        Address savedAddress = addressRepository.save(address);
        //creating response
        ResponseAddressDto responseAddressDto = new ResponseAddressDto();
        responseAddressDto.setCity(savedAddress.getCity());
        responseAddressDto.setStreetAddress(savedAddress.getStreetAddress());
        responseAddressDto.setId(savedAddress.getId());
        responseAddressDto.setState(savedAddress.getState());
        responseAddressDto.setZipCode(savedAddress.getZipCode());
        responseAddressDto.setUserId(savedAddress.getUser().getId());
        return responseAddressDto;
    }
//delete
    @Override
    public void deleteAddress(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(()-> new ResourceNotFoundException("Address","id",addressId));
        addressRepository.delete(address);
    }
}
