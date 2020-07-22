package com.github.roman1306.registry.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.UUID;

public interface RecordDao<R> {

    @Nullable
    R findById(@NonNull UUID recordId);

    @NonNull
    Page<R> findByUserId(@NonNull String username, @NonNull Pageable pageable);
}
