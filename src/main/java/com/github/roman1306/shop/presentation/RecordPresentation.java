package com.github.roman1306.shop.presentation;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecordPresentation {

    private LocalDateTime dateTime;
    private String speciality;
    private Doctor doctor;

    @Data
    public static class Doctor {
        private String name;
        private String description;
    }
}
