package com.eraasoft.spring.controller;

import com.eraasoft.spring.service.AccountService;
import com.eraasoft.spring.service.DTO.AccountDTO;
import com.eraasoft.spring.service.DTO.EraaSoftSchoolDTO;
import com.eraasoft.spring.service.token.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class AccountController {

    private AccountService accountService;
    private TokenHandler tokenHandler;

    @Autowired
    public AccountController(AccountService accountService, TokenHandler tokenHandler) {
        this.accountService = accountService;
        this.tokenHandler = tokenHandler;
    }

    @GetMapping("/user")
    ResponseEntity<AccountDTO> getStudentByUserName(@RequestParam String userName){return ResponseEntity.ok(accountService.getByUserName(userName));}
    @GetMapping("/get-token")
    ResponseEntity<String> getToken(String userName){return ResponseEntity.ok(tokenHandler.createToken(accountService.getByUserName(userName))) ;}
}
