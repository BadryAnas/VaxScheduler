package com.clinic.vaxschedular.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.vaxschedular.Entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

    Optional<Role> findByEmail(String email);

}
