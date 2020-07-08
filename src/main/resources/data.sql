insert into roles(name) values ('MANAGER'), ('CUSTOMER');
insert into users(name, password) values ('root', 'root');
insert into user_roles(username, role) values ('root', 'MANAGER');