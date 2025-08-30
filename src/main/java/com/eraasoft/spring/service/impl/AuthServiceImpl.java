package com.eraasoft.spring.service.impl;

import com.eraasoft.spring.controller.vm.AuthRequestVm;
import com.eraasoft.spring.controller.vm.AuthResponseVm;
import com.eraasoft.spring.service.AccountService;
import com.eraasoft.spring.service.AuthService;

import com.eraasoft.spring.service.DTO.AccountDTO;
import com.eraasoft.spring.service.token.TokenHandler;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {
    private AccountService accountService;
    private TokenHandler tokenHandler;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(AccountService accountService, TokenHandler tokenHandler, PasswordEncoder passwordEncoder) {
        this.accountService = accountService;
        this.tokenHandler = tokenHandler;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponseVm login(AuthRequestVm authRequestVm) {
        AccountDTO accountDTO = accountService.getByUserName(authRequestVm.getUserName());
        if( !passwordEncoder.matches(authRequestVm.getPassword(), accountDTO.getPassword())){
            throw new RuntimeException("invalid password");
        }

        return new AuthResponseVm(tokenHandler.createToken(accountDTO));
    }

    @Override
    public AuthResponseVm signup(AccountDTO accountDTO) throws SystemException {
        AccountDTO savedAccountDTO =  accountService.createAccount(accountDTO);
        if (Objects.isNull(savedAccountDTO)){
            throw new SystemException("account Not Created");
        }
        return new AuthResponseVm(tokenHandler.createToken(savedAccountDTO));
    }
}
