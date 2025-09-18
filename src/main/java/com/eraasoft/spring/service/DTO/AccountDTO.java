package com.eraasoft.spring.service.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {
//    @Schema(description = "Unique identifier of the chef", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private String userName;
    private String phone;
    private String address;
    private String password;
    private List<RoleDTO> roles;
}
