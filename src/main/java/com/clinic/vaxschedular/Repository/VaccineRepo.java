package com.clinic.vaxschedular.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.clinic.vaxschedular.Entity.Vaccine;

@Repository
@EnableJpaRepositories
public interface VaccineRepo extends JpaRepository<Vaccine, Integer> {

    Optional<Vaccine> findByVaccineName(String name);

}
