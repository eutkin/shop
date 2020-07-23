package com.github.roman1306.registry.dao.impl;

import com.github.roman1306.registry.dao.UserDao;
import com.github.roman1306.registry.entity.Role;
import com.github.roman1306.registry.entity.User;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Repository
public class JdbcUserDao implements UserDao {

    private final JdbcOperations jdbc;
    private final SqlHolder sqlHolder;

    public JdbcUserDao(
            @NonNull JdbcOperations jdbc,
            @NonNull SqlHolder sqlHolder
    ) {
        this.jdbc = jdbc;
        this.sqlHolder = sqlHolder;
    }

    @Override
    @Transactional(readOnly = true)
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        String sql = this.sqlHolder.load("sql/get-user.sql");
        final AtomicReference<User> userHolder = new AtomicReference<>();
        this.jdbc.query(sql, (rs, i) -> {
            String name = rs.getString("username");
            String password = rs.getString("password");
            String role = rs.getString("role");
            userHolder.compareAndSet(null, new User());
            return userHolder.updateAndGet(user -> user.setUsername(name)
                    .setPassword(password)
                    .setRoles(Set.of(new Role().setName(role))));
        }, username);
        return userHolder.get();

    }

    @Override
    @Transactional
    public User createUser(User user) {
        String userSql = this.sqlHolder.load("sql/create-user.sql");
        String userRoleSql = this.sqlHolder.load("sql/create-user-role.sql");
        final String roleSql = this.sqlHolder
                .load("sql/create-" + (user.isDoctor() ? "doctor" : "patient") + ".sql");
        this.jdbc.update(userSql,
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getBirthDate(),
                user.getPassword()
        );
        for (GrantedAuthority role : user.getAuthorities()) {
            this.jdbc.update(userRoleSql, user.getUsername(), role.getAuthority());
        }
        this.jdbc.update(roleSql, ps -> {
            ps.setObject(1, UUID.randomUUID());
            ps.setObject(2, user.getUsername());
        });
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    @NonNull
    public List<Role> getRoles() {
        try {
            return this.jdbc
                    .query("select * from roles where name <> 'ADMIN'",
                            (rs, i) -> new Role().setName(rs.getString("name")));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Collections.emptyList();
        }
    }
}
