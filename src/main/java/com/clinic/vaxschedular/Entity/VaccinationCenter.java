package com.clinic.vaxschedular.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.OneToMany;
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
@Table(name = "VaccinationCenter")
public class VaccinationCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Vaccine_Center_id")
    private int id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "Vaccine_Center_Name", nullable = false, unique = true)
    private String centerName;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "phone_Num", nullable = false)
    private String phoneNum;

    @Column(name = "Admin_Id", nullable = false)
    private int adminId;

    // Patients relation
    // @JsonIgnore
    @OneToMany(mappedBy = "vaccinationCenter", cascade = CascadeType.ALL)
    private List<Patient> patients;

    // Set<ChildEntity> children = new HashSet<>()
    // Admin-Relation
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Admin_Id", referencedColumnName = "id", insertable = false, updatable = false)
    private Admin admin;

    @JsonIgnore
    // Many-To-Many Relation with Vaccine
    @ManyToMany(mappedBy = "vaccinationCenters", cascade = CascadeType.ALL)
    private List<Vaccine> vaccines;

}
