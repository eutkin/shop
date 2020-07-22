package com.github.roman1306.shop.dao;

import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface PatientRecordDao<R> extends RecordDao<R> {

    /**
     * Проверяет доступность слота.
     * @param slotId идентификатор слота
     * @return {@literal true} если слот занят, иначе {@literal false}
     */
    boolean busySlot(@NonNull UUID slotId);

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

}
