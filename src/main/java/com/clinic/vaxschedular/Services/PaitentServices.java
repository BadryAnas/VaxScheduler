package com.clinic.vaxschedular.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clinic.vaxschedular.DTO.LoginDTO;
import com.clinic.vaxschedular.DTO.Reservation_DTO;
import com.clinic.vaxschedular.DTO.VaccineCenterDTO;
import com.clinic.vaxschedular.DTO.VaccineDTO;
import com.clinic.vaxschedular.Entity.Patient;

@Service
public interface PaitentServices {

    String Register(Patient patient);

    String login(LoginDTO loginDTO);

    List<VaccineCenterDTO> listVaccinationCenters();

    List<VaccineDTO> listVaccine();

    String reseveVaccination(Reservation_DTO test);

    // String viewCertification(Certificate certificate);

}
