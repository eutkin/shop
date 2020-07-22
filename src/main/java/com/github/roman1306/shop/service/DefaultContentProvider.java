package com.github.roman1306.shop.service;

import com.github.roman1306.shop.dao.DictionaryDao;
import com.github.roman1306.shop.entity.User;
import com.github.roman1306.shop.presentation.DepartmentView;
import com.github.roman1306.shop.presentation.SpecialityView;
import com.github.roman1306.shop.service.spi.ContentProvider;
import com.github.roman1306.shop.service.spi.RecordRoleBasedStrategy;
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
    private final DictionaryDao<SpecialityView> specialities;

    @NonNull
    private final Collection<RecordRoleBasedStrategy<?>> records;

    public DefaultContentProvider(
            @NonNull DictionaryDao<DepartmentView> departments,
            @NonNull DictionaryDao<SpecialityView> specialities,
            @NonNull Collection<RecordRoleBasedStrategy<?>> records
    ) {
        this.departments = departments;
        this.specialities = specialities;
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
    public Page<?> records(User user, Pageable pageable) {
       return this.records.stream()
                .filter(strategy -> strategy.support(user))
                .map(strategy -> strategy.records(user, pageable))
                .findFirst()
                .orElse(Page.empty(pageable));
    }
}
