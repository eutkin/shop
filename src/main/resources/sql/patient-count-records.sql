select count(*) as id
from records r
         join patients p on r.patient_id = p.patient_id
         join users u on p.user_id = u.name
where u.name = ?