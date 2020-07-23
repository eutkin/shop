select distinct d.department_id, name, address, image
from departments d
    join doctor_slots ds on d.department_id = ds.departament_id