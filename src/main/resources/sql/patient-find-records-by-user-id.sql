select ds.time_slot                 as datetime,
       s.name                       as speciality,
       du.name || ' ' || du.surname as doctor_name,
       d.description                as doctor_desc,
       d.photo                      as doctor_photo,
       de.name                      as department
from records r
         inner join patients p on r.patient_id = p.patient_id
         inner join users u on p.user_id = u.username
         inner join doctor_slots ds on r.slot_id = ds.slot_id
         inner join doctors d on ds.doctor_id = d.doctor_id
         inner join users du on d.user_id = du.username
         inner join specialties s on ds.speciality_id = s.speciality_id
         inner join departments de on ds.departament_id = de.department_id
where u.username = ?
order by ds.time_slot desc
