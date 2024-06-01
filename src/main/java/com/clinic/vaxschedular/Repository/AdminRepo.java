package com.clinic.vaxschedular.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.clinic.vaxschedular.Entity.Admin;

@Repository
@EnableJpaRepositories
public interface AdminRepo extends JpaRepository<Admin, Integer> {

    Optional<Admin> findByEmail(String email);

    Optional<Admin> findBySsn(long ssn);

}
