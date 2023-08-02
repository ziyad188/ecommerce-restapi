package ziyad.com.ecommercerestapi.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ziyad.com.ecommercerestapi.entity.Roles;
import ziyad.com.ecommercerestapi.entity.User;
import ziyad.com.ecommercerestapi.exception.ProductApiException;
import ziyad.com.ecommercerestapi.payload.LoginDto;
import ziyad.com.ecommercerestapi.payload.SignUpDto;
import ziyad.com.ecommercerestapi.repository.RolesRepository;
import ziyad.com.ecommercerestapi.repository.UserRepository;
import ziyad.com.ecommercerestapi.security.JwtAuthenticationFilter;
import ziyad.com.ecommercerestapi.security.JwtTokenProvider;
import ziyad.com.ecommercerestapi.service.AuthService;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }

    @Override
    public String register(SignUpDto signUpDto) {
        //check wheather user already there
        if(userRepository.existsByUsername(signUpDto.getUsername())) {
            throw new ProductApiException(HttpStatus.BAD_REQUEST, "Username is already registered");
        }
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            throw new ProductApiException(HttpStatus.BAD_REQUEST, "Email is already registered");
        }
        User user = new User();
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Set<Roles> roles = new HashSet<>();
        Roles userRole =rolesRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        return "User registered successfully";

    }
}
