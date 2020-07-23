select count(*) as id
from records r
         join doctors d on r.patient_id = d.doctor_id
         join users u on d.user_id = u.username
where u.username = ?