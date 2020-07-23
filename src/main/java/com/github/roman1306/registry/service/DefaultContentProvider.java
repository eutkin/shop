package com.github.roman1306.registry.service;

import com.github.roman1306.registry.dao.DictionaryDao;
import com.github.roman1306.registry.entity.User;
import com.github.roman1306.registry.presentation.DepartmentView;
import com.github.roman1306.registry.presentation.DoctorView;
import com.github.roman1306.registry.presentation.SpecialityView;
import com.github.roman1306.registry.service.spi.ContentProvider;
import com.github.roman1306.registry.service.spi.RecordRoleBasedStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class DefaultContentProvider implements ContentProvider {

    @NonNull
    private final DictionaryDao<DepartmentView> departments;

    @NonNull
    private final DictionaryDao<DepartmentView> availableDepartments;

    @NonNull
    private final DictionaryDao<SpecialityView> specialities;

    @NonNull
    private final DictionaryDao<DoctorView> doctors;

    @NonNull
    private final Collection<RecordRoleBasedStrategy<?>> records;

    public DefaultContentProvider(
            @Qualifier("departments-dao") @NonNull DictionaryDao<DepartmentView> departments,
            @Qualifier("available-departments-dao") @NonNull DictionaryDao<DepartmentView> availableDepartments,
            @NonNull DictionaryDao<SpecialityView> specialities,
            @NonNull DictionaryDao<DoctorView> doctors,
            @NonNull Collection<RecordRoleBasedStrategy<?>> records
    ) {
        this.departments = departments;
        this.specialities = specialities;
        this.availableDepartments = availableDepartments;
        this.doctors = doctors;
        this.records = records;
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
    public List<DepartmentView> availableDepartments() {
        return this.availableDepartments.load();
    }

    @Override
    public List<DoctorView> doctors() {
        return this.doctors.load();
    }

    @Override
    public Page<?> records(User user, Pageable pageable) {
        return this.records.stream()
                .filter(strategy -> strategy.support(user))
                .map(strategy -> strategy.records(user, pageable))
                .findFirst()
                .orElse(Page.empty(pageable));
    }
}
