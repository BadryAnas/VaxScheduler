package com.clinic.vaxschedular.Services;

import com.clinic.vaxschedular.Entity.Vaccine;
import org.springframework.stereotype.Service;

import com.clinic.vaxschedular.DTO.PatientDTO;
import com.clinic.vaxschedular.Entity.Admin;
import com.clinic.vaxschedular.Entity.VaccinationCenter;

import java.util.List;
// import com.clinic.vaxschedular.Entity.Vaccine;

@Service
public interface AdminService {

    String removePatient(int id);

    String addAdmin(Admin admin);

    // String acceptPatient(Patient patient);

    // String rejectPatient(Patient patient);

    String addVaccinationCenter(VaccinationCenter vaccinationCenter);

    String deleteVaccinationCenter(int id);

    String updateVaccinationCenter(int id, VaccinationCenter vaccinationCenter);

    List<VaccinationCenter> listVaccinationCenter();

    String createVaccine(Vaccine vaccine);

    List<Vaccine> listVaccine();

    String deleteVaccine(int id);

    String updateVaccine(int id, Vaccine vaccine);

    List<PatientDTO> listPatients();
}
