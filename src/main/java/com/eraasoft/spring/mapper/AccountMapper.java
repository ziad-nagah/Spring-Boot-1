package com.eraasoft.spring.mapper;


import com.eraasoft.spring.model.Account;
import com.eraasoft.spring.service.DTO.AccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper ACCOUNT_MAPPER = Mappers.getMapper(AccountMapper.class);


    Account toAccount(AccountDTO accountDTO);

    AccountDTO toAccountDTO(Account account);
   List<Account> toAccountList(List<AccountDTO> accountDTOS);
   List<AccountDTO> toAccountDTOList(List<Account> accounts);


}
