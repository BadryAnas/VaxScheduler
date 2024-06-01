package com.clinic.vaxschedular.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.vaxschedular.Entity.Vaccine;
import com.clinic.vaxschedular.Entity.VaccineCenter_Vaccine;

public interface VaccineCenter_Vaccine_Repo extends JpaRepository<VaccineCenter_Vaccine, Integer> {
    List<Vaccine> findByVaccineId(int id);
}
