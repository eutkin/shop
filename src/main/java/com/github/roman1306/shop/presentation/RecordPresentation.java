package com.github.roman1306.shop.presentation;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class RecordPresentation {

    private LocalDateTime dateTime;
    private String speciality;
    private Doctor doctor;

    @Data
    @Accessors(chain = true)
    public static class Doctor {
        private String name;
        private String description;
    }
}
