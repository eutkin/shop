select p.patient_id
from patients p
         join users u on p.user_id = u.username
where u.username = ?
