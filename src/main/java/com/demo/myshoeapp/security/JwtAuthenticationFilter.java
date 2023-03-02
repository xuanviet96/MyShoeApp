package com.demo.myshoeapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("AUTHORIZATION");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (!jwtTokenUtil.validateToken(token)) {
                filterChain.doFilter(request, response);
                return;
            }
            String username = jwtTokenUtil.getSubject(token);
            UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(username);
            List<String> roles = jwtTokenUtil.getClaims(token);

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for(String role : roles) {
                SimpleGrantedAuthority simp = new SimpleGrantedAuthority(role);
                authorities.add(simp);
            }
//            roles.stream().map(role -> authorities.add(new SimpleGrantedAuthority(role)));
            System.out.println(authorities.size());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
