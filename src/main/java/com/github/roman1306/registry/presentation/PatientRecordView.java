package com.github.roman1306.registry.presentation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class PatientRecordView {

    @JsonFormat(pattern = "dd MMMM yyyy HH:mm")
    private LocalDateTime dateTime;
    private String speciality;
    private DoctorView doctor;
    private String department;

}
