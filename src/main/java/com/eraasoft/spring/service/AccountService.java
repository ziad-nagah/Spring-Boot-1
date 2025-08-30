package com.eraasoft.spring.service;


import com.eraasoft.spring.service.DTO.AccountDTO;

import java.util.List;

public interface AccountService {



    AccountDTO getByUserName(String username);
    AccountDTO createAccount(AccountDTO accountDTO);

}
