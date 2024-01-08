package com.abhijeet.noteapp.Security;

import com.abhijeet.noteapp.Util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        if(requestUri.startsWith("/api/auth")){
            filterChain.doFilter(request, response);
            return;
        }
        String requestHeader = request.getHeader("Authorization");
        String userName = null;
        String token = null;
        try{
            token = requestHeader.substring(7);
            userName = jwtUtil.getClaimsFromToken(token).getSubject();
        } catch (Exception e){
            e.printStackTrace();
        }
        if(userName!=null){
            UserDetails userDetails= userDetailsService.loadUserByUsername(userName);
            Boolean validated = jwtUtil.validateToken(token, userDetails);
            if(validated){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, null);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }else {
            throw new BadCredentialsException("Token invalid!!");
        }

        filterChain.doFilter(request, response);
    }
}
