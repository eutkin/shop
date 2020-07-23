--liquibase formatted sql
-- changeset roman1306:init_roles dbms:postgresql
insert into roles(name)
values ('DOCTOR'),
       ('PATIENT');