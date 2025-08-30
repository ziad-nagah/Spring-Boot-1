package com.eraasoft.spring.repo;

import com.eraasoft.spring.model.EraaSoftSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EraaSoftSchoolRepo extends JpaRepository<EraaSoftSchool,Long> {
    Optional<EraaSoftSchool> findByUserName(String userName);
@Query("select ess from EraaSoftSchool ess where ess.name =:name")
Optional<EraaSoftSchool> extractByName(@Param("name") String name);
}
