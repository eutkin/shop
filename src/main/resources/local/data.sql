insert into roles(name)
values ('ADMIN'),
       ('DOCTOR'),
       ('PATIENT');
insert into users(name, password)
values ('root', 'root');
insert into user_roles(username, role)
values ('root', 'ADMIN');

INSERT INTO public.specialties (speciality_id, name)
VALUES ('910f143f-24b7-4a63-92d5-b46ad57c46e0', 'Терапевт'),
       ('37c1e40c-5a37-4895-aa75-df7ab07dbfd5', 'Отоларинголог');