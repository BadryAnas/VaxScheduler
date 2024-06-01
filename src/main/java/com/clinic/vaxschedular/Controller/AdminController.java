package com.clinic.vaxschedular.Controller;

import com.clinic.vaxschedular.Entity.Vaccine;
import com.clinic.vaxschedular.Response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.clinic.vaxschedular.DTO.PatientDTO;
import com.clinic.vaxschedular.Entity.Admin;
import com.clinic.vaxschedular.Entity.VaccinationCenter;
import com.clinic.vaxschedular.Services.AdminService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/hello")
    public String sayWelcome() {
        return "Welcome";
    }

    @PostMapping("/Add_Center")
    public ResponseEntity<Response> add_Center(@RequestBody VaccinationCenter vaccinationCenter) {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.value(),
                adminService.addVaccinationCenter(vaccinationCenter)),
                HttpStatus.CREATED);
    }

    @PostMapping("/Add_Admin")
    public ResponseEntity<Response> add_Aenter(@RequestBody Admin admin) {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.value(),
                adminService.addAdmin(admin)),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/deletePatient/{id}")
    public ResponseEntity<Response> deletePatient(@PathVariable int id) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.value(), adminService.removePatient(id)),
                HttpStatus.OK);
    }

    // --------------------------************************

    @GetMapping("/listAllVaccinationCenters")
    public List<VaccinationCenter> getAllVaccinationCenters() {
        return adminService.listVaccinationCenter();
    }

    @PutMapping("/updateVaccinationCenter/{id}")
    public ResponseEntity<Response> updateVaccinationCenter(@PathVariable int id,
            @RequestBody VaccinationCenter vaccinationCenter) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.value(),
                adminService.updateVaccinationCenter(id, vaccinationCenter)),
                HttpStatus.OK);
    }

    @DeleteMapping("/deleteVaccinationCenter/{id}")
    public ResponseEntity<Response> deleteVaccinationCenter(@PathVariable int id) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.value(),
                adminService.deleteVaccinationCenter(id)),
                HttpStatus.OK);
    }

    @PostMapping("/addVaccine")
    public ResponseEntity<Response> createNewVaccine(@RequestBody Vaccine vaccine) {
        return new ResponseEntity<>(
                new Response(HttpStatus.CREATED.value(),
                        adminService.createVaccine(vaccine)),
                HttpStatus.CREATED);
    }

    // --------------------------************************

    @GetMapping("/listAllVaccines")
    public List<Vaccine> getAllVaccines() {
        return adminService.listVaccine();
    }

    @DeleteMapping("/deleteVaccine/{id}")
    public String deleteVaccine(@PathVariable int id) {
        return adminService.deleteVaccine(id);
    }

    @PutMapping("/updateVaccine/{id}")
    public String updateVaccine(@PathVariable int id, @RequestBody Vaccine vaccine) {
        return adminService.updateVaccine(id, vaccine);
    }

    @GetMapping("/ListPatient")
    public List<PatientDTO> listPatients() {
        return adminService.listPatients();
    }

    @GetMapping("listVaccine")
    public List<Vaccine> listVaccines() {
        return adminService.listVaccine();
    }
}
