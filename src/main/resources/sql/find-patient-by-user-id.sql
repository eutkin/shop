select p.patient_id
from patients p
         join users u on p.user_id = u.name
where u.name = ?
