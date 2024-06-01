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
public class VaccineDTO {

    private String vaccineName;
    private String durationBetweenDoses;
    private String precautions;

    // public VaccineDTO(String vaccineName, String durationBetweenDoses, String
    // precautions) {
    // this.vaccineName = vaccineName;
    // this.durationBetweenDoses = durationBetweenDoses;
    // this.precautions = precautions;
    // }

}
