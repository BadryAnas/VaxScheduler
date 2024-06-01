package com.clinic.vaxschedular.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "Patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Patient_id")
    private int id;

    // @Convert(converter = AesEncryptor.class)
    @Column(name = "Ssn", nullable = false, unique = true)
    private long Ssn;

    // @Convert(converter = AesEncryptor.class)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    // @Convert(converter = AesEncryptor.class)
    @Column(name = "last_name", nullable = false)
    private String lastName;
    // @Convert(converter = AesEncryptor.class)
    @Column(name = "patient_email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    // @Convert(converter = AesEncryptor.class)
    @Column(name = "certification", nullable = true)
    private String certification;

    @Column(name = "vaccination_Center_id", nullable = true)
    private int vaccineCenter;

    // Relation With Vaccine Center
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "vaccination_Center_id", referencedColumnName = "Vaccine_Center_id", insertable = false, updatable = false)
    private VaccinationCenter vaccinationCenter;

    // Relation with Vaccine
    @JsonIgnore
    @ManyToMany(mappedBy = "patients", cascade = CascadeType.ALL)
    private List<Vaccine> vaccines;
}
