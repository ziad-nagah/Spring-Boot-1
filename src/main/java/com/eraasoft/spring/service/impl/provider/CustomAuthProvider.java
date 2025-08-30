
package com.eraasoft.spring.service.impl.provider;

import com.eraasoft.spring.mapper.AccountMapper;
import com.eraasoft.spring.model.Account;
import com.eraasoft.spring.repo.AccountRepo;
import com.eraasoft.spring.service.AccountService;
import com.eraasoft.spring.service.DTO.AccountDTO;
import jakarta.security.auth.message.config.AuthConfigProvider;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Component
public class CustomAuthProvider implements AuthenticationProvider {
//
    private AccountService accountService;
//    @Autowired
    public CustomAuthProvider(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        Optional<Account> account = accountRepo.findByUserName(authentication.getPrincipal().toString());
        AccountDTO accountDTO = accountService.getByUserName(authentication.getPrincipal().toString());
        if(/*!account.isPresent()*/ !authentication.getCredentials().toString().equals(accountDTO.getPassword())){
            throw new RuntimeException(/*"user name not found"*/"invalid password");
        }
        List<SimpleGrantedAuthority> roles = getAuthorities(accountDTO);

        return new UsernamePasswordAuthenticationToken(
                authentication.getPrincipal().toString(),//userName
                authentication.getCredentials().toString(),//password
                roles);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    private List<SimpleGrantedAuthority> getAuthorities(AccountDTO accountDTO) {
        return  accountDTO.getRoles().stream().map(
                roleDTO -> new SimpleGrantedAuthority(/**/roleDTO.getRoleName())).collect(Collectors.toList());
}}
