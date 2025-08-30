package com.eraasoft.spring.mapper;


import com.eraasoft.spring.model.Role;
import com.eraasoft.spring.service.DTO.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "sprind")
public interface RoleMapper {

    Role toRole(RoleDTO roleDTO);
    RoleDTO toRoleDTO(Role role);
    List<Role> toRoleList(List<RoleDTO> roleDTOS);
    List<RoleDTO> toRoleDTOList(List<Role> roles);

}