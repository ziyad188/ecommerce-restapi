package ziyad.com.ecommercerestapi.security;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ziyad.com.ecommercerestapi.entity.User;
import ziyad.com.ecommercerestapi.repository.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CustomUserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRespository) {
        this.userRepository = userRespository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user =userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail).orElseThrow(()-> new UsernameNotFoundException("User not found with username or email:"+usernameOrEmail));
        Set<GrantedAuthority> authorities = user.getRoles().stream().map((role)-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());



        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }


}