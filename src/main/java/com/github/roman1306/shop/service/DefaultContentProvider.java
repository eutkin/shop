package com.github.roman1306.shop.service;

import com.github.roman1306.shop.dao.DictionaryDao;
import com.github.roman1306.shop.entity.User;
import com.github.roman1306.shop.presentation.DepartmentView;
import com.github.roman1306.shop.presentation.SpecialityView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultContentProvider implements ContentProvider {

    @NonNull
    private final DictionaryDao<DepartmentView> departments;

    @NonNull
    private final DictionaryDao<SpecialityView> specialities;

    @NonNull
    private final PatientRecordService patientRecordService;

    public DefaultContentProvider(
            @NonNull DictionaryDao<DepartmentView> departments,
            @NonNull DictionaryDao<SpecialityView> specialities,
            @NonNull PatientRecordService patientRecordService
    ) {
        this.departments = departments;
        this.specialities = specialities;
        this.patientRecordService = patientRecordService;
    }


    @Override
    public List<SpecialityView> specialities() {
        return this.specialities.load();
    }

    @Override
    public List<DepartmentView> departments() {
        return this.departments.load();
    }

    @Override
    public Page<Object> records(User user, Pageable pageable) {
        return Page.empty();
    }
}
