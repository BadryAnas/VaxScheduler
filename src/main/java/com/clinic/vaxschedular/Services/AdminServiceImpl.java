package com.clinic.vaxschedular.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.clinic.vaxschedular.DTO.PatientDTO;
import com.clinic.vaxschedular.Entity.*;
import com.clinic.vaxschedular.Repository.*;
import com.clinic.vaxschedular.Response.DuplicateEntryException;
import com.clinic.vaxschedular.Response.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private VaccinationCenterRepo vaccineCenterRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private VaccineRepo vaccineRepo;

    @Override
    public String removePatient(int id) throws NotFoundException {
        Patient existPatient = patientRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Patient Not Found."));
        patientRepo.delete(existPatient);
        return "Patient Removed Successfully!";
    }

    @Override
    public String addVaccinationCenter(VaccinationCenter vaccinationCenter) {

        vaccineCenterRepo.findByCenterName(vaccinationCenter.getCenterName()).ifPresent(existCenter -> {
            throw new DuplicateEntryException(
                    "Center with Name " + vaccinationCenter.getCenterName() + " already exists.");
        });
        vaccineCenterRepo.findByEmail(vaccinationCenter.getEmail()).ifPresent(existCenter -> {
            throw new DuplicateEntryException(
                    "Center with Email " + vaccinationCenter.getEmail() + " already exists.");
        });
        vaccinationCenter.setPassword(passwordEncoder.encode(vaccinationCenter.getPassword()));
        vaccineCenterRepo.save(vaccinationCenter);
        Role role = new Role("CENTER", vaccinationCenter.getEmail(), vaccinationCenter.getPassword());
        roleRepo.save(role);
        return "Center Added Successfully!";
    }

    @Override
    public String addAdmin(Admin admin) throws DuplicateEntryException {
        Optional<Admin> existAdmin = adminRepo.findByEmail(admin.getEmail());

        adminRepo.findBySsn(admin.getSsn()).ifPresent(existingAdmin -> {
            throw new DuplicateEntryException("Admin with SSN " + admin.getSsn() + " already exists.");
        });

        if (existAdmin.isPresent()) {
            throw new DuplicateEntryException("Admin with " + admin.getEmail() + " already exists.");
        } else {
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            adminRepo.save(admin);
            Role role = new Role("ADMIN", admin.getEmail(), admin.getPassword());
            roleRepo.save(role);
            return "Admin Added Successfully!";
        }
    }

    @Override
    public List<PatientDTO> listPatients() {
        List<Patient> patients = patientRepo.findAll();
        List<PatientDTO> patientdDtos = new ArrayList<>();

        for (Patient patient : patients) {
            PatientDTO patientDTO = new PatientDTO();
            patientDTO.setId(patient.getId());
            patientDTO.setEmail(patient.getEmail());
            patientDTO.setPassword(patient.getPassword());
            patientdDtos.add(patientDTO);
        }
        return patientdDtos;
    }

    @Override
    public List<VaccinationCenter> listVaccinationCenter() {

        List<VaccinationCenter> vaccinationCenters = vaccineCenterRepo.findAll();
        return vaccinationCenters;
    }

    @Override
    public String updateVaccinationCenter(int id, VaccinationCenter vaccinationCenter) {

        VaccinationCenter existCenter = vaccineCenterRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Center With id : " + id + " Does not Exist"));
        existCenter.setAdminId(vaccinationCenter.getAdminId());
        existCenter.setCenterName(vaccinationCenter.getCenterName());
        existCenter.setPassword(vaccinationCenter.getPassword());
        existCenter.setEmail(vaccinationCenter.getEmail());
        existCenter.setLocation(vaccinationCenter.getLocation());
        existCenter.setPhoneNum(vaccinationCenter.getPhoneNum());
        vaccineCenterRepo.save(existCenter);
        return "Vaccination Ceneterr Updated Successfully!";
    }

    @Override
    public String deleteVaccinationCenter(int id) {
        if (vaccineCenterRepo.findById(id).isPresent()) {
            vaccineCenterRepo.deleteById(id);
            return "Vaccination Center Deleted Successfully";
        } else {
            throw new NotFoundException("Center With id : " + id + " Does not Exist");
        }
    }

    @Override
    public String createVaccine(Vaccine vaccine) {

        vaccineRepo.findByVaccineName(vaccine.getVaccineName()).ifPresent(existVaccine -> {
            throw new DuplicateEntryException(
                    "Vaccine With Name " + vaccine.getVaccineName() + " Already Exist");
        });
        vaccineRepo.save(vaccine);
        return "Vaccine Added Successfully";
    }

    @Override
    public List<Vaccine> listVaccine() {
        return vaccineRepo.findAll();
    }

    @Override
    public String deleteVaccine(int id) {
        if (vaccineRepo.findById(id).isPresent()) {
            vaccineRepo.deleteById(id);
            return "Deleted Successfully";
        } else {
            throw new NotFoundException("Vaccine With id : " + id + " Does not Exist");
        }
    }

    @Override
    public String updateVaccine(int id, Vaccine vaccine) {

        Vaccine existVaccine = vaccineRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Vaccine With id : " + id + " Does not Exist"));
        existVaccine.setVaccineName(vaccine.getVaccineName());
        existVaccine.setDurationBetweenDoses(vaccine.getDurationBetweenDoses());
        existVaccine.setPrecautions(vaccine.getPrecautions());
        vaccineRepo.save(existVaccine);
        return "Updaed Successfully";

    }
}
