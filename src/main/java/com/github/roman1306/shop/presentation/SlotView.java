package com.github.roman1306.shop.presentation;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class SlotView {

    private UUID id;

    private LocalDateTime time;

    private DoctorView doctor;

    private boolean available;
}
