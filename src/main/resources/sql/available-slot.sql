select s.slot_id,
       s.time_slot,
       s.doctor_id,
       du.name || ' ' || du.surname                           as doctor_name,
       d.description                                          as doctor_desc,
       d.photo as doctor_photo,
       case when r.record_id is null then true else false end as available
from doctor_slots s
         join doctors d on s.doctor_id = d.doctor_id
         join users du on d.user_id = du.username
         left join records r on s.slot_id = r.slot_id
where s.speciality_id = ?
  and s.departament_id = ?
  and s.time_slot between ? and ?