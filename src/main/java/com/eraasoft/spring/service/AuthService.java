package com.eraasoft.spring.service;

import com.eraasoft.spring.controller.vm.AuthRequestVm;
import com.eraasoft.spring.controller.vm.AuthResponseVm;
import com.eraasoft.spring.service.DTO.AccountDTO;
import jakarta.transaction.SystemException;

public interface AuthService {
    AuthResponseVm login(AuthRequestVm authRequestVm);
    AuthResponseVm signup(AccountDTO accountDTO) throws SystemException;
}
