package com.clinic.vaxschedular.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.clinic.vaxschedular.Entity.Patient;

@Repository
@EnableJpaRepositories
public interface PatientRepo extends JpaRepository<Patient, Integer> {

    Optional<Patient> findByEmail(String email);

    Optional<Patient> findByEmailAndPassword(String email, String Password);

    List<Patient> findByVaccinationCenterId(int id);

}
