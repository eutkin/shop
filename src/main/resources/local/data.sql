insert into roles(name) values ('ADMIN'), ('DOCTOR'), ('PATIENT');
insert into users(name, password) values ('root', 'root');
insert into user_roles(username, role) values ('root', 'ADMIN');