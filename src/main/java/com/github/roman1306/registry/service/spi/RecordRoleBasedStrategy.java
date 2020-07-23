package com.github.roman1306.registry.service.spi;

import com.github.roman1306.registry.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

/**
 * Стратегия поиска записей. Алгоритм поиска и содержимое зависит от пользователя,
 * который запрашивает данные (пациент или врач).
 *
 * @param <T> Тип контента
 */
public interface RecordRoleBasedStrategy<T> {

    /**
     * Возвращает страницы с записями.
     *
     * @param user     пользователь
     * @param pageable запрос страницы
     * @return страница с записями
     */
    @NonNull
    Page<T> records(@NonNull User user, @NonNull Pageable pageable);

    /**
     * Определяет применимость стратегии по пользователю.
     *
     * @param user пользователь
     * @return {@literal true} если стратегия подходит для пользователя, иначе {@literal false}
     */
    boolean support(@NonNull User user);
}
