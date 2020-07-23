package com.github.roman1306.registry.service.spi;

import com.github.roman1306.registry.entity.User;
import com.github.roman1306.registry.presentation.DepartmentView;
import com.github.roman1306.registry.presentation.DoctorView;
import com.github.roman1306.registry.presentation.SpecialityView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * Предоставляет контент для html страниц приложения: департаменты, записи, специальности.
 * Не содержит логики по обработке информации.
 */
public interface ContentProvider {
    /**
     * @return специальности
     */
    @NonNull
    List<SpecialityView> specialities();

    /**
     * @return департаменты
     */
    @NonNull
    List<DepartmentView> departments();

    /**
     * @return департаменты, доступные для записи на прием
     */
    @NonNull
    List<DepartmentView> availableDepartments();

    /**
     * @return доктора
     */
    @NonNull
    List<DoctorView> doctors();

    /**
     * Записи на прием. Для врача и пациента отличаются содержимым.
     *
     * @param user     пользователь
     * @param pageable запрос страницы
     * @return страница с записями
     */
    @NonNull
    Page<?> records(@NonNull User user, @NonNull Pageable pageable);
}
