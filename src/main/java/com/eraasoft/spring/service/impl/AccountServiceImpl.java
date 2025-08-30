package com.eraasoft.spring.service.impl;


import com.eraasoft.spring.mapper.AccountMapper;
import com.eraasoft.spring.model.Account;
import com.eraasoft.spring.model.Role;
import com.eraasoft.spring.repo.AccountRepo;
import com.eraasoft.spring.service.AccountService;
import com.eraasoft.spring.service.DTO.AccountDTO;
import com.eraasoft.spring.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepo accountRepo;
    private AccountMapper accountMapper;
//    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public AccountServiceImpl(AccountRepo accountRepo, AccountMapper accountMapper,@Lazy PasswordEncoder passwordEncoder) {
        this.accountRepo = accountRepo;
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AccountDTO getByUserName(String userName){
       Optional<Account> account = accountRepo.findByUserName(userName);
       if(!account.isPresent()){
           throw new RuntimeException("userName not exist");
       }
        return accountMapper.toAccountDTO(account.get());
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {

        if (!accountRepo.findByUserName(accountDTO.getUserName()).isPresent()){
            throw new RuntimeException("User name not exist");
        }
        Account account = accountMapper.toAccount(accountDTO);
        account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        List<Role> roles = Collections.singletonList(new Role("USER",account));
        account.setRoles(roles);
        return accountMapper.toAccountDTO(accountRepo.save(account));
    }
}
