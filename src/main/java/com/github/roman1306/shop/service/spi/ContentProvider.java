package com.github.roman1306.shop.service.spi;

import com.github.roman1306.shop.entity.User;
import com.github.roman1306.shop.presentation.DepartmentView;
import com.github.roman1306.shop.presentation.SpecialityView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ContentProvider {

    @NonNull
    List<SpecialityView> specialities();

    @NonNull
    List<DepartmentView> departments();

    @NonNull
    Page<?> records(@NonNull User user, @NonNull Pageable pageable);
}
