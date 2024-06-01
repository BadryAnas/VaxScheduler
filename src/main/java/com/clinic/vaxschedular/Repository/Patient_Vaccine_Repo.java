package com.clinic.vaxschedular.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.vaxschedular.Entity.Patient_Vaccine;

public interface Patient_Vaccine_Repo extends JpaRepository<Patient_Vaccine, Integer> {

}
