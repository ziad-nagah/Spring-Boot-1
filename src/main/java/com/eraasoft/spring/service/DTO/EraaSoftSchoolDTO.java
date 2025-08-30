package com.eraasoft.spring.service.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EraaSoftSchoolDTO {

    private Long id ;
    @NotBlank(message = "name.notnull")
    private String name;
    @NotBlank(message = "user.name.notnull")
    private String userName;
    @NotBlank(message = "password.notnull")
    private String password;
    @NotNull(message = "age.notnull")
    private int age;

}
