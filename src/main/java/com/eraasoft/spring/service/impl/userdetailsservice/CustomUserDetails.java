package com.eraasoft.spring.service.impl.userdetailsservice;

import com.eraasoft.spring.service.DTO.AccountDTO;
import com.eraasoft.spring.service.DTO.RoleDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {
    AccountDTO accountDTO;

    public CustomUserDetails(AccountDTO accountDTO) {
        this.accountDTO = accountDTO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  accountDTO.getRoles().stream().map(roleDTO -> new SimpleGrantedAuthority(/**/roleDTO.getRoleName())).collect(Collectors.toList());

//        List<SimpleGrantedAuthority> roles = new ArrayList<>();
//
//        for(RoleDTO roleDTO : accountDTO.getRoles()){
//            roles.add(new SimpleGrantedAuthority(roleDTO.getRoleName()));
//        }
//        return roles;
    }

    @Override
    public String getPassword() {
        return /**/accountDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return accountDTO.getUserName();
    }
}
