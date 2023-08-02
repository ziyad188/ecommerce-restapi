package ziyad.com.ecommercerestapi.service;

import ziyad.com.ecommercerestapi.payload.LoginDto;
import ziyad.com.ecommercerestapi.payload.SignUpDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(SignUpDto signUpDto);
}
