package com.github.roman1306.shop.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

import java.util.UUID;

@Data
public class RecordPatientRequest {

    private final UUID specialityId;
    private final UUID departmentId;
    private final UUID slotId;

    @JsonCreator
    public RecordPatientRequest(UUID specialityId, UUID departmentId, UUID slotId) {
        this.specialityId = specialityId;
        this.departmentId = departmentId;
        this.slotId = slotId;
    }
}
