package com.github.roman1306.shop.presentation;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class DoctorRecordView {

    @DateTimeFormat(pattern = "dd MMMM yyyy HH:mm")
    private LocalDateTime dateTime;
    private String speciality;
    private PatientView doctor;
    private String department;

}
