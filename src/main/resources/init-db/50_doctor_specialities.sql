--liquibase formatted sql
-- changeset roman1306:init_doctor_specialities context:demo dbms:postgresql
INSERT INTO public.doctor_specialities (doctor_id, speciality_id)
VALUES ('2abf0925-9561-4a8f-86f5-4466123955a5', '37c1e40c-5a37-4895-aa75-df7ab07dbfd5');
INSERT INTO public.doctor_specialities (doctor_id, speciality_id)
VALUES ('2abf0925-9561-4a8f-86f5-4466123955a5', '910f143f-24b7-4a63-92d5-b46ad57c46e0');
INSERT INTO public.doctor_specialities (doctor_id, speciality_id)
VALUES ('5426973c-67d6-41e4-9ebb-5f4325d125b9', '910f143f-24b7-4a63-92d5-b46ad57c46e0');
INSERT INTO public.doctor_specialities (doctor_id, speciality_id)
VALUES ('c2699c3b-67c5-40cb-8938-2cfc509ba252', '256357c1-7e2a-493b-a8c2-94d23a75047e');
INSERT INTO public.doctor_specialities (doctor_id, speciality_id)
VALUES ('558c8888-c90e-4dcd-a8f4-de0342a319a2', '910f143f-24b7-4a63-92d5-b46ad57c46e0');
INSERT INTO public.doctor_specialities (doctor_id, speciality_id)
VALUES ('558c8888-c90e-4dcd-a8f4-de0342a319a2', '0ef37226-f91c-408f-a2c3-2d79b6c68ce1');
INSERT INTO public.doctor_specialities (doctor_id, speciality_id)
VALUES ('558c8888-c90e-4dcd-a8f4-de0342a319a2', 'a8b3bc99-1575-4ab1-a34a-8d46d6314b57');
INSERT INTO public.doctor_specialities (doctor_id, speciality_id)
VALUES ('144507ec-5795-495f-82aa-786e9f287c62', '1e6419c7-2754-43a2-be3a-f71d8a2162d3');
INSERT INTO public.doctor_specialities (doctor_id, speciality_id)
VALUES ('144507ec-5795-495f-82aa-786e9f287c62', '37c1e40c-5a37-4895-aa75-df7ab07dbfd5');
INSERT INTO public.doctor_specialities (doctor_id, speciality_id)
VALUES ('3615c793-95b8-4b9f-95cc-6ad3911671b5', 'a8b3bc99-1575-4ab1-a34a-8d46d6314b57');