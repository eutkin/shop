select ds.time_slot                 as datetime,
       s.name                       as speciality,
       du.name || ' ' || du.surname as doctor_name,
       d.description                as doctor_desc,
       d.photo                      as doctor_photo,
       d2.name                      as department
from records r
         inner join doctor_slots ds on r.slot_id = ds.slot_id
         inner join doctors d on ds.doctor_id = d.doctor_id
         inner join users du on d.user_id = du.username
         inner join specialties s on ds.speciality_id = s.speciality_id
         inner join departments d2 on ds.departament_id = d2.department_id
where r.record_id = ?
