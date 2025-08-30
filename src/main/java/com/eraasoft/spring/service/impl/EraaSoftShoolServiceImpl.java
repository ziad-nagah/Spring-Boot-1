package com.eraasoft.spring.service.impl;

import com.eraasoft.spring.mapper.EraaSoftMapper;
import com.eraasoft.spring.model.EraaSoftSchool;
import com.eraasoft.spring.service.DTO.EraaSoftSchoolDTO;
import com.eraasoft.spring.repo.EraaSoftSchoolRepo;
import com.eraasoft.spring.service.EraaSoftShoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EraaSoftShoolServiceImpl implements EraaSoftShoolService {
private EraaSoftSchoolRepo eraaSoftSchoolRepo;
private EraaSoftMapper eraaSoftMapper;
//private ModelMapper modelMapper;
@Autowired
public EraaSoftShoolServiceImpl(EraaSoftSchoolRepo eraaSoftSchoolRepo,EraaSoftMapper eraaSoftMapper/* , ModelMapper modelMapper*/){this.eraaSoftSchoolRepo=eraaSoftSchoolRepo;this.eraaSoftMapper=eraaSoftMapper;}//this.modelMapper=modelMapper;}

    @Override
    public EraaSoftSchoolDTO save(EraaSoftSchoolDTO eraaSoftSchoolDTO) {
    if(Objects.nonNull(eraaSoftSchoolDTO.getId())){
        throw new RuntimeException("ID must be null");

    }
        Optional<EraaSoftSchool> eraaSoftSchoolOptional = eraaSoftSchoolRepo.findByUserName(eraaSoftSchoolDTO.getUserName());
    if (eraaSoftSchoolOptional.isPresent()){throw new RuntimeException("User name is exist");}

//        EraaSoftSchool eraaSoftSchool = modelMapper.map(eraaSoftSchoolDTO,EraaSoftSchool.class);
        EraaSoftSchool eraaSoftSchool = eraaSoftMapper.toEraaSoftSchool(eraaSoftSchoolDTO);
        eraaSoftSchool = eraaSoftSchoolRepo.save(eraaSoftSchool);
        eraaSoftSchoolDTO.setId(eraaSoftSchool.getId()); // ask
        return eraaSoftSchoolDTO;
    }

    @Override
    public EraaSoftSchoolDTO update(EraaSoftSchoolDTO eraaSoftSchoolDTO) {
        if(Objects.isNull(eraaSoftSchoolDTO.getId())){
            throw new RuntimeException("id must not be null");

        }
//        EraaSoftSchool eraaSoftSchool = modelMapper.map(eraaSoftSchoolDTO,EraaSoftSchool.class);
        EraaSoftSchool eraaSoftSchool = eraaSoftMapper.toEraaSoftSchool(eraaSoftSchoolDTO);
        eraaSoftSchool = eraaSoftSchoolRepo.save(eraaSoftSchool);

        return eraaSoftSchoolDTO;
    }

    @Override
    public boolean delete(Long id) {

         Optional <EraaSoftSchool> EraaSoftSchoolDTOOptional =  eraaSoftSchoolRepo.findById(id);
          if(EraaSoftSchoolDTOOptional.isEmpty()){
          return false;
        }
//          EraaSoftSchoolDTO EraaSoftSchoolDTO = EraaSoftSchoolDTOOptional.get();

          eraaSoftSchoolRepo.deleteById(id);
          return true;

    }

    @Override
    public List<EraaSoftSchoolDTO> getAll() {
    List<EraaSoftSchool> eraaSoftSchools = eraaSoftSchoolRepo.findAll();
    if(CollectionUtils.isEmpty(eraaSoftSchools)){
        return new ArrayList<>();
    }
//        return eraaSoftSchools.stream().map(eraaSoftSchool ->
//        modelMapper.map(eraaSoftSchool,EraaSoftSchoolDTO.class)).collect(Collectors.toList());
//    }
        return eraaSoftSchools.stream().map(eraaSoftSchool ->
                eraaSoftMapper.toEraaSoftSchoolDTO(eraaSoftSchool)).collect(Collectors.toList());
    }

    @Override
    public EraaSoftSchoolDTO getById(Long id) {
        Optional <EraaSoftSchool> eraaSoftSchoolOptional =  eraaSoftSchoolRepo.findById(id);
        if(eraaSoftSchoolOptional.isEmpty()){
            return null;
        }


//        return modelMapper.map(eraaSoftSchoolOptional.get(),EraaSoftSchoolDTO.class);
        return  eraaSoftMapper.toEraaSoftSchoolDTO(eraaSoftSchoolOptional.get());
    }
    @Override
    public EraaSoftSchoolDTO getByUserName(String userName) {
        Optional <EraaSoftSchool> eraaSoftSchoolOptional = eraaSoftSchoolRepo.extractByName(userName);
        if(eraaSoftSchoolOptional.isEmpty()){return null;}


//        return modelMapper.map(eraaSoftSchoolOptional.get(),EraaSoftSchoolDTO.class);
        return  eraaSoftMapper.toEraaSoftSchoolDTO(eraaSoftSchoolOptional.get());
    }

    @Override
    public EraaSoftSchoolDTO extByName(String name) {
        Optional <EraaSoftSchool> eraaSoftSchoolOptional = eraaSoftSchoolRepo.extractByName(name);
        if(eraaSoftSchoolOptional.isEmpty()){return null;}



//        return modelMapper.map(eraaSoftSchoolOptional.get(),EraaSoftSchoolDTO.class);
            return  eraaSoftMapper.toEraaSoftSchoolDTO(eraaSoftSchoolOptional.get());
}


}
