package com.github.roman1306.registry.presentation;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class PatientView {

    private String name;

    private LocalDate birthDate;

}
