select d2.name                       as department,
       pu.name || ' ' || pu.surname as patient_name,
       pu.birth_date                as patient_bd,
       ds.time_slot                 as datetime
from records r
         join patients p on r.patient_id = p.patient_id
         join doctor_slots ds on r.slot_id = ds.slot_id
         join doctors d on ds.doctor_id = d.doctor_id
         join users u on d.user_id = u.username
         join users pu on p.user_id = pu.username
         join departments d2 on ds.departament_id = d2.department_id
where u.username = ?
order by ds.time_slot asc