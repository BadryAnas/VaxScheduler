package com.clinic.vaxschedular.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "Patient_Vaccine")
public class Patient_Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @Column(name = "patient_id")
    private int patientId;

    @Column(name = "vaccine_id")
    private int vaccineId;

    @ManyToOne
    @JoinColumn(name = "Patient_id", referencedColumnName = "Patient_id", insertable = false, updatable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "vaccine_id", referencedColumnName = "vaccine_id", insertable = false, updatable = false)
    private Vaccine vaccine;

    @Column(name = "doses")
    private int doses;

}
