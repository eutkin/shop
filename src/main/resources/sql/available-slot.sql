select s.slot_id,
       s.time_slot,
       s.doctor_id,
       d.name        as doctor_name,
       d.description as doctor_desc,
       case when r.record_id is null then true else false end as available
from doctor_slots s
         join doctors d on s.doctor_id = d.doctor_id
         left join records r on s.slot_id = r.slot_id
where s.speciality_id = ?
  and s.departament_id = ?
  and s.time_slot between ? and ?