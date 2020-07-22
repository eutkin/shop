package com.github.roman1306.registry.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
@FieldNameConstants
public class User implements UserDetails {

    @Length(min = 5, message = "validation.user.username.length")
    private String username;

    @Length(min = 6, message = "validation.user.password.length")
    private String password;

    @NotNull(message = "validation.user.birth-date.not-null")
    private LocalDate birthDate;

    @NotBlank(message = "validation.user.name.not-blank")
    private String name;

    @NotBlank(message = "validation.user.surname.not-blank")
    private String surname;

    @NotEmpty(message = "validation.user.roles.not-empty")
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean isPatient() {
        return this.getRoles().stream().map(Role::getAuthority)
                .anyMatch("PATIENT"::equalsIgnoreCase);
    }

    public boolean isDoctor() {
        return this.getRoles().stream().map(Role::getAuthority)
                .anyMatch("DOCTOR"::equalsIgnoreCase);
    }
}
