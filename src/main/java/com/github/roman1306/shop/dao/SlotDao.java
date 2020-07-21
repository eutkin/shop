package com.github.roman1306.shop.dao;

import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface SlotDao<T> {

    @NonNull
    List<T> findAvailable(
            @NonNull UUID speciality,
            @NonNull UUID departmentId,
            @NonNull LocalDateTime startInclusive,
            @NonNull LocalDateTime endInclusive
    );
}
