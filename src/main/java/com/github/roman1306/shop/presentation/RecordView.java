package com.github.roman1306.shop.presentation;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class RecordView {

    private LocalDateTime dateTime;
    private String speciality;
    private DoctorView doctor;

}
