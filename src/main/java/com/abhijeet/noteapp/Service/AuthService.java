package com.abhijeet.noteapp.Service;

import com.abhijeet.noteapp.Util.JwtUtil;
import com.abhijeet.noteapp.Entity.Users;
import com.abhijeet.noteapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    public String signUp(Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user.getUsername()+" registered successfully.";
    }

    public Users findUserName(String username){
        Optional<Users> user = userRepository.findByUsername(username);
        return user.orElse(null);
    }

    public String signIn(Users user){
        authenticateUser(user.getUsername(), user.getPassword());
        String token = jwtUtil.generateToken(user.getUsername());
        return token;
    }

    private void authenticateUser(String userName, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
        try {
            authenticationManager.authenticate(authenticationToken);
        }catch (Exception e){
            throw new BadCredentialsException("Invalid username or password");
        }
    }


}
