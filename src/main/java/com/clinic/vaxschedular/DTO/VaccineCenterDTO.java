package com.clinic.vaxschedular.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VaccineCenterDTO {

    private String name;
    private String email;
    private String locatoin;
    private List<VaccineDTO> vaccines;

}
