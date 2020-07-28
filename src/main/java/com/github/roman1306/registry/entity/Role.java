package com.github.roman1306.registry.entity;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    private String name;

    public String getName() {
        return name;
    }

    public Role setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + name;
    }
}
