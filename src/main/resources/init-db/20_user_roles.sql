--liquibase formatted sql
-- changeset roman1306:init_user_roles context:demo dbms:postgresql
INSERT INTO public.user_roles (username, role) VALUES ('Oleg228', 'DOCTOR');
INSERT INTO public.user_roles (username, role) VALUES ('FOX', 'DOCTOR');
INSERT INTO public.user_roles (username, role) VALUES ('Flower', 'DOCTOR');
INSERT INTO public.user_roles (username, role) VALUES ('Pashket', 'DOCTOR');
INSERT INTO public.user_roles (username, role) VALUES ('Fisher', 'DOCTOR');
INSERT INTO public.user_roles (username, role) VALUES ('Angela', 'DOCTOR');
INSERT INTO public.user_roles (username, role) VALUES ('Allushka', 'PATIENT');
INSERT INTO public.user_roles (username, role) VALUES ('Mitya', 'PATIENT');
INSERT INTO public.user_roles (username, role) VALUES ('Lermontov', 'PATIENT');
INSERT INTO public.user_roles (username, role) VALUES ('Singer', 'PATIENT');
INSERT INTO public.user_roles (username, role) VALUES ('PoliceWoman', 'PATIENT');