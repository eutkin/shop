package com.github.roman1306.shop.service;

import com.github.roman1306.shop.presentation.DepartmentView;
import com.github.roman1306.shop.presentation.SpecialityView;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ContentProvider {

    @NonNull
    List<SpecialityView> specialities();

    @NonNull
    List<DepartmentView> departments();
}
