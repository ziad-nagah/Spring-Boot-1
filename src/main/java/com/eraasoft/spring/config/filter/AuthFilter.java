package com.eraasoft.spring.config.filter;

import com.eraasoft.spring.service.DTO.AccountDTO;
import com.eraasoft.spring.service.token.TokenHandler;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AuthFilter extends OncePerRequestFilter {
//    @Autowired
    private TokenHandler tokenHandler;

    public AuthFilter(TokenHandler tokenHandler) {
        this.tokenHandler = tokenHandler;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if (request.getRequestURI().contains("login") || request.getRequestURI().contains("signup") ){return true;}
     return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (Objects.isNull(token) || !token.startsWith("Bearer")){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        token = token.substring(7);
        AccountDTO accountDTO = tokenHandler.validateToken(token);
        if (Objects.isNull(accountDTO)){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        List<SimpleGrantedAuthority> roles = getAuthorities(accountDTO);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(
                accountDTO,//userName
                accountDTO.getPassword(),//password
                roles);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request,response);
    }

    private List<SimpleGrantedAuthority> getAuthorities(AccountDTO accountDTO) {
        return  accountDTO.getRoles().stream().map(
                roleDTO -> new SimpleGrantedAuthority(/**/roleDTO.getRoleName())).collect(Collectors.toList());

}
}
