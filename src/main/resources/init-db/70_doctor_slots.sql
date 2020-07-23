--liquibase formatted sql
-- changeset roman1306:init_slots context:demo dbms:postgresql
with timeslots as (
    select generate_series(
                   (current_date || ' 08:00')::timestamp,
                   (current_date || ' 21:00')::timestamp,
                   interval '30' minute
               ) as time_slot
    union
    select generate_series(
                   ((current_date + integer '1') || ' 08:00')::timestamp,
                   ((current_date + integer '1') || ' 21:00')::timestamp,
                   interval '30' minute
               ) as time_slot
    union
    select generate_series(
                   ((current_date + integer '2') || ' 08:00')::timestamp,
                   ((current_date + integer '2') || ' 21:00')::timestamp,
                   interval '30' minute
               ) as time_slot
),
     other as (
         select d.doctor_id,
                min(ds.speciality_id::text)::uuid as specialy_id,
                min(dp.department_id::text)::uuid as department_id
         from doctors d
                  join doctor_specialities ds on d.doctor_id = ds.doctor_id
                  cross join departments dp
         group by d.doctor_id
     )
insert
into doctor_slots(slot_id, doctor_id, departament_id, speciality_id, time_slot)
select uuid_generate_v4() as slot_id, other.doctor_id, other.department_id, other.specialy_id, t.time_slot
from timeslots t
         cross join other;


