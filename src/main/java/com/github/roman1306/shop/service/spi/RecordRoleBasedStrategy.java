package com.github.roman1306.shop.service.spi;

import com.github.roman1306.shop.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

public interface RecordRoleBasedStrategy<T> {

    @NonNull
    Page<T> records(@NonNull User user, @NonNull Pageable pageable);

    boolean support(@NonNull User user);
}
