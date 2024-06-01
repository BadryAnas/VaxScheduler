package com.clinic.vaxschedular.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Vaccine")
public class Vaccine {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Vaccine_id")
    @Id
    private int id;

    @Column(name = "vaccine_Name", nullable = false, unique = true)
    private String vaccineName;

    @Column(name = "duration_Between_Doses", nullable = false)
    private String durationBetweenDoses;

    @Column(name = "precautions", nullable = false)
    private String precautions;

    @Column(name = "Admin_Id", nullable = false)
    private int adminId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Admin_Id", referencedColumnName = "id", insertable = false, updatable = false)
    private Admin admin;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "Patient_Vaccine", joinColumns = @JoinColumn(name = "Vaccine_id"), inverseJoinColumns = @JoinColumn(name = "Patient_id"))
    private List<Patient> patients;

    @ManyToMany
    @JoinTable(name = "VaccineCenter_Vaccine", joinColumns = @JoinColumn(name = "Vaccine_id"), inverseJoinColumns = @JoinColumn(name = "Vaccine_Center_id"))
    private List<VaccinationCenter> vaccinationCenters;

}
