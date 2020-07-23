with data as (
    select p.patient_id, ds.slot_id
    from patients p
             cross join (
        select min(slot_id::text)::uuid as slot_id
        from doctor_slots
        group by time_slot::date, doctor_id, departament_id, speciality_id
    ) ds
)
insert
into records(record_id, patient_id, slot_id)
select uuid_generate_v4() as record_id, patient_id, slot_id
from data;