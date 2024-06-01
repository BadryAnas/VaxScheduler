package com.clinic.vaxschedular.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.clinic.vaxschedular.Entity.VaccinationCenter;

@Repository
@EnableJpaRepositories
public interface VaccinationCenterRepo extends JpaRepository<VaccinationCenter, Integer> {

    Optional<VaccinationCenter> findByEmail(String email);

    Optional<VaccinationCenter> findByCenterName(String name);

}
