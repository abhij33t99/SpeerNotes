package com.abhijeet.noteapp.Controller;

import com.abhijeet.noteapp.Entity.Users;
import com.abhijeet.noteapp.Service.AuthService;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    @RateLimiter(name = "authRateLimiter", fallbackMethod = "authFallback")
    public ResponseEntity<String> signUp(@Valid @RequestBody Users user){
        if(authService.findUserName(user.getUsername()) != null){
            return new ResponseEntity<>("User already exists.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(authService.signUp(user), HttpStatus.OK);
    }

    @PostMapping("/login")
    @RateLimiter(name = "authRateLimiter", fallbackMethod = "authFallback")
    public ResponseEntity<String> signIn(@Valid @RequestBody Users user){
        return new ResponseEntity<>(authService.signIn(user), HttpStatus.OK);
    }

    public ResponseEntity<String> authFallback(Principal principal, RequestNotPermitted ex){
        return new ResponseEntity<>("Request limit exceeded", HttpStatus.TOO_MANY_REQUESTS);
    }
}
