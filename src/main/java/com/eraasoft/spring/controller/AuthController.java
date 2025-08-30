package com.eraasoft.spring.controller;

import com.eraasoft.spring.controller.vm.AuthRequestVm;
import com.eraasoft.spring.controller.vm.AuthResponseVm;
import com.eraasoft.spring.service.AuthService;
import com.eraasoft.spring.service.DTO.AccountDTO;
import com.eraasoft.spring.service.DTO.EraaSoftSchoolDTO;
import com.eraasoft.spring.service.impl.AuthServiceImpl;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    ResponseEntity<AuthResponseVm> login(@RequestBody @Valid AuthRequestVm authRequestVm){
        return ResponseEntity.ok(authService.login(authRequestVm));
    }

    @PostMapping("/signup")
    ResponseEntity<AuthResponseVm> signup(@RequestBody @Valid AccountDTO accountDTO) throws SystemException {
        return ResponseEntity.ok(authService.signup(accountDTO));}
}
