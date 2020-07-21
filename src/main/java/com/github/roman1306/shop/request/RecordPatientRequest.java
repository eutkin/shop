package com.github.roman1306.shop.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.lang.NonNull;

import java.util.UUID;

import static com.github.roman1306.shop.request.RecordPatientRequest.Fields.*;

@Data
@FieldNameConstants
public class RecordPatientRequest {

    @NonNull
    private final UUID slotId;

    @JsonCreator
    public RecordPatientRequest(@JsonProperty(Fields.slotId) @NonNull UUID slotId) {
        this.slotId = slotId;
    }
}
