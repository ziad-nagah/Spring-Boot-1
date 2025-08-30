package com.eraasoft.spring.mapper;

import com.eraasoft.spring.model.EraaSoftSchool;
import com.eraasoft.spring.service.DTO.EraaSoftSchoolDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EraaSoftMapper {
//    EraaSoftMapper ERAA_SOFT_MAPPER = Mappers.getMapper(EraaSoftMapper.class);
    EraaSoftSchool toEraaSoftSchool(EraaSoftSchoolDTO eraaSoftSchoolDTO);
    EraaSoftSchoolDTO toEraaSoftSchoolDTO(EraaSoftSchool eraaSoftSchool);
    List<EraaSoftSchool> toEraaSoftSchoolList(List<EraaSoftSchoolDTO> eraaSoftSchoolDTOs);
    List<EraaSoftSchoolDTO> toEraaSoftSchoolDTOList(List<EraaSoftSchool> eraaSoftSchools);


}
