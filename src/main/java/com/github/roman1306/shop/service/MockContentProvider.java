package com.github.roman1306.shop.service;

import com.github.roman1306.shop.presentation.DepartmentView;
import com.github.roman1306.shop.presentation.SpecialityView;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class MockContentProvider implements ContentProvider {

    @Override
    public List<SpecialityView> specialities() {
        return List.of(new SpecialityView().setId(UUID.randomUUID()).setName("Терапевт"));
    }

    @Override
    public List<DepartmentView> departments() {
        return List.of(
                new DepartmentView()
                        .setId(UUID.randomUUID())
                        .setName("На Вавилова")
                        .setAddress("Проспект Вавилова 12а")
        );
    }
}
