select ds.time_slot as datetime, s.name as speciality, d.name as doctor_name, d.description as doctor_desc
from records r
         inner join doctor_slots ds on r.slot_id = ds.slot_id
         inner join doctors d on ds.doctor_id = d.doctor_id
         inner join specialties s on ds.speciality_id = s.speciality_id
where r.record_id = ?
