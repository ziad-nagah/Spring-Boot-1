package com.eraasoft.spring.service;
import com.eraasoft.spring.service.DTO.EraaSoftSchoolDTO;

import java.util.List;

public interface EraaSoftShoolService {


   EraaSoftSchoolDTO save(EraaSoftSchoolDTO EraaSoftSchoolDTO);
    EraaSoftSchoolDTO update(EraaSoftSchoolDTO EraaSoftSchoolDTO);
    boolean delete(Long id);
    List<EraaSoftSchoolDTO> getAll();
    EraaSoftSchoolDTO getById(Long id);
    EraaSoftSchoolDTO getByUserName(String userName);

    EraaSoftSchoolDTO extByName(String name);

}
