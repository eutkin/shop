package com.github.roman1306.shop.dao;

import com.github.roman1306.shop.presentation.RecordPresentation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface RecordDao {

    Optional<UUID> findPatientIdByUserId(@NonNull String userId);

    /**
     * Бронирует слот для пациента.
     *
     * @param slotId    Идентификатор слота
     * @param patientId Идентификатор пациента
     * @return идентификатор записи
     */
    @NonNull
    UUID bookSlot(@NonNull UUID slotId, @NonNull UUID patientId);

    /**
     * Проверяет доступность слота.
     * @param slotId идентификатор слота
     * @return {@literal true} если слот занят, иначе {@literal false}
     */
    boolean checkSlot(@NonNull UUID slotId);

    @NonNull
    RecordPresentation findById(UUID recordId);

    @NonNull
    Page<RecordPresentation> findByUserId(@NonNull String username, @NonNull Pageable pageable);
}
