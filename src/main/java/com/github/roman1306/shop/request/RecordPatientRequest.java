package com.github.roman1306.shop.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

import java.util.UUID;

@Data
public class RecordPatientRequest {

    private final UUID slotId;

    @JsonCreator
    public RecordPatientRequest(UUID slotId) {
        this.slotId = slotId;
    }
}
