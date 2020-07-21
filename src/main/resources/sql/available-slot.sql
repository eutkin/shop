select s.slot_id, s.time_slot, s.doctor_id, d.name as doctor_name, d.description as doctor_desc
from doctor_slots s
         inner join doctors d on s.doctor_id = d.doctor_id
where s.speciality_id = ?
  and s.departament_id = ?
  and s.time_slot between ? and ?