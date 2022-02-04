package ai.sukhrob.splitwise.service.auth;

import ai.sukhrob.splitwise.domain.auth.User;
import ai.sukhrob.splitwise.payload.LoginDto;
import ai.sukhrob.splitwise.payload.ResponseApi;
import ai.sukhrob.splitwise.repository.UserRepository;
import ai.sukhrob.splitwise.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        Optional<User> byPhone = userRepository.findByPhone(phoneNumber);
        if (byPhone.isPresent())
            return byPhone.get();

        throw new UsernameNotFoundException("User not found!!!");
    }

    public ResponseApi login(LoginDto loginDto) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getPhoneNumber(), loginDto.getPassword()
            ));
            User user = (User) authenticate.getPrincipal();
            String token = jwtProvider.generateToken(user.getPhone());
            return new ResponseApi(token,true);
        }catch (BadCredentialsException e){
            return new ResponseApi("Password or PhoneNumber is incorrect",false);
        }
    }

    public ResponseApi signUp(){

    }


}
