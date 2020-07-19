package com.github.roman1306.shop.dao.impl;

import com.github.roman1306.shop.dao.UserDao;
import com.github.roman1306.shop.entity.Role;
import com.github.roman1306.shop.entity.User;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Transactional
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
        final User user = new User();
        this.jdbc.query(sql, (rs, i) -> {
            String name = rs.getString("username");
            String password = rs.getString("password");
            String role = rs.getString("role");
            return user.setName(name)
                    .setPassword(password)
                    .addRole(new Role().setName(role));
        }, username);
        return user;

    }

    @Override
    @Transactional
    public User createUser(User user) {
        String userSql = this.sqlHolder.load("sql/create-user.sql");
        String userRoleSql = this.sqlHolder.load("sql/create-user-role.sql");
        this.jdbc.update(userSql, user.getUsername(), user.getPassword());
        for (GrantedAuthority role : user.getAuthorities()) {
            this.jdbc.update(userRoleSql, user.getUsername(), role.getAuthority());
        }
        return user;
    }
}
