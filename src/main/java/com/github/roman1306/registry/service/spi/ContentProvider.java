package com.github.roman1306.registry.service.spi;

import com.github.roman1306.registry.entity.User;
import com.github.roman1306.registry.presentation.DepartmentView;
import com.github.roman1306.registry.presentation.DoctorView;
import com.github.roman1306.registry.presentation.SpecialityView;
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
    List<DepartmentView> availableDepartments();

    @NonNull
    List<DoctorView> doctors();

    @NonNull
    Page<?> records(@NonNull User user, @NonNull Pageable pageable);
}
