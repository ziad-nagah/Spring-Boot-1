package com.eraasoft.spring.repo;

import com.eraasoft.spring.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
//    Optional<Role> findByRole(String role);
}
