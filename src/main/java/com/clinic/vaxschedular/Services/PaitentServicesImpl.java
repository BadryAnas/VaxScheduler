package com.clinic.vaxschedular.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clinic.vaxschedular.DTO.LoginDTO;
import com.clinic.vaxschedular.DTO.Reservation_DTO;
import com.clinic.vaxschedular.DTO.VaccineCenterDTO;
import com.clinic.vaxschedular.DTO.VaccineDTO;
import com.clinic.vaxschedular.Entity.Patient;
import com.clinic.vaxschedular.Entity.Patient_Vaccine;
import com.clinic.vaxschedular.Entity.Role;
import com.clinic.vaxschedular.Entity.VaccinationCenter;
import com.clinic.vaxschedular.Entity.Vaccine;
import com.clinic.vaxschedular.Repository.PatientRepo;
import com.clinic.vaxschedular.Repository.Patient_Vaccine_Repo;
import com.clinic.vaxschedular.Repository.RoleRepo;
import com.clinic.vaxschedular.Repository.VaccinationCenterRepo;
import com.clinic.vaxschedular.Repository.VaccineRepo;
import com.clinic.vaxschedular.Response.NotFoundException;

@Service
public class PaitentServicesImpl implements PaitentServices {

    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private VaccineRepo vaccineRepo;

    @Autowired
    private Patient_Vaccine_Repo patient_Vaccine_Repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VaccinationCenterRepo vaccinationCenterRepo;

    @Override
    public String Register(Patient patient) {

        Optional<Patient> optionalPatient = patientRepo.findByEmail(patient.getEmail());

        if (optionalPatient.isPresent()) {
            throw new RuntimeException("Patient with email " + patient.getEmail() + " already exists");
        } else {
            Patient newPatient = new Patient(patient.getId(), patient.getSsn(),
                    patient.getFirstName(), patient.getLastName(), patient.getEmail(),
                    this.passwordEncoder.encode(patient.getPassword()), patient.getCertification(),
                    patient.getVaccineCenter(),
                    patient.getVaccinationCenter(),
                    patient.getVaccines());
            patientRepo.save(newPatient);
            Role role = new Role("PATIENT", newPatient.getEmail(), newPatient.getPassword());
            roleRepo.save(role);
        }
        return "Patient registered successfully!";
    }

    public String login(LoginDTO loginDTO) {
        Optional<Patient> patient = patientRepo.findByEmail(loginDTO.getEmail());
        if (patient.isPresent()) {
            String encodedPassword = patient.get().getPassword();
            boolean isPwdRight = passwordEncoder.matches(loginDTO.getPassword(), encodedPassword);
            if (isPwdRight) {
                Optional<Patient> existPatient = patientRepo.findByEmailAndPassword(loginDTO.getEmail(),
                        encodedPassword);
                if (existPatient.isPresent()) {
                    return "Login Success";
                } else {
                    throw new NotFoundException("Login Failed");
                }
            } else {
                throw new NotFoundException("Wrong Password");
            }
        } else {
            throw new NotFoundException("Email Doesn't Exist");
        }
    }

    @Override
    public String reseveVaccination(Reservation_DTO test) {
        Optional<Vaccine> existVaccine = vaccineRepo.findById(test.getVaccine_id());

        Optional<Patient> existPatient = patientRepo.findById(test.getPatient_id());

        if (existVaccine.isPresent() && existPatient.isPresent()) {
            Patient_Vaccine patient_Vaccine = new Patient_Vaccine();
            patient_Vaccine.setVaccineId(existVaccine.get().getId());
            patient_Vaccine.setPatientId((existPatient.get().getId()));
            patient_Vaccine.setDoses(0);
            patient_Vaccine_Repo.save(patient_Vaccine);
            return "Done!";
        } else {
            return "Failed";
        }

    }

    @Override
    public List<VaccineCenterDTO> listVaccinationCenters() {
        List<VaccinationCenter> centers = vaccinationCenterRepo.findAll();
        List<VaccineCenterDTO> centerDTOs = new ArrayList<>();
        for (VaccinationCenter center : centers) {
            VaccineCenterDTO centerDTO = new VaccineCenterDTO();
            centerDTO.setName(center.getCenterName());
            centerDTO.setLocatoin(center.getLocation());
            centerDTO.setEmail(center.getEmail());

            List<VaccineDTO> vaccineDTOs = new ArrayList<>();
            for (Vaccine vaccine : center.getVaccines()) {
                VaccineDTO vaccineDTO = new VaccineDTO();
                vaccineDTO.setDurationBetweenDoses(vaccine.getDurationBetweenDoses());
                vaccineDTO.setPrecautions(vaccine.getPrecautions());
                vaccineDTO.setVaccineName(vaccine.getVaccineName());
                vaccineDTOs.add(vaccineDTO);
            }
            centerDTO.setVaccines(vaccineDTOs);
            centerDTOs.add(centerDTO);
        }
        return centerDTOs;
    }

    @Override
    public List<VaccineDTO> listVaccine() {
        List<Vaccine> vaccines = vaccineRepo.findAll();
        List<VaccineDTO> vaccineDTOs = new ArrayList<>();
        for (Vaccine vaccine : vaccines) {
            VaccineDTO vaccineDTO = new VaccineDTO();
            vaccineDTO.setDurationBetweenDoses(vaccine.getDurationBetweenDoses());
            vaccineDTO.setPrecautions(vaccine.getPrecautions());
            vaccineDTO.setVaccineName(vaccine.getVaccineName());

            // List<VaccineCenterDTO> vaccineCenterDTOs = new ArrayList<>();
            // for (VaccinationCenter vaccinationCenter : vaccine.getVaccinationCenters()) {
            // VaccineCenterDTO vaccineCenterDTO = new VaccineCenterDTO();
            // vaccineCenterDTO.setEmail(vaccinationCenter.getEmail());
            // vaccineCenterDTO.setLocatoin(vaccinationCenter.getLocation());
            // vaccineCenterDTO.setName(vaccinationCenter.getCenterName());
            // vaccineCenterDTOs.add(vaccineCenterDTO);
            // }
            // vaccineDTO.setVaccineCenters(vaccineCenterDTOs);
            vaccineDTOs.add(vaccineDTO);
        }
        return vaccineDTOs;
    }

}
