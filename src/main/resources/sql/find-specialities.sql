select distinct s.speciality_id, s.name
from specialties s
    join doctor_slots ds on s.speciality_id = ds.speciality_id