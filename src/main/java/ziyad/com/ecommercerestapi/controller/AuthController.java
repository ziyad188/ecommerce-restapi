package ziyad.com.ecommercerestapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ziyad.com.ecommercerestapi.payload.JwtAuthResponse;
import ziyad.com.ecommercerestapi.payload.LoginDto;
import ziyad.com.ecommercerestapi.payload.SignUpDto;
import ziyad.com.ecommercerestapi.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    //rest api for login
    @PostMapping(value = {"/login","/signin"})
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);

    }
    @PostMapping(value = {"/register","/signup"})
    public ResponseEntity<String> register(@RequestBody SignUpDto signUpDto){
        String response = authService.register(signUpDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
