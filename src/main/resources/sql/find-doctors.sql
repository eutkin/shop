SELECT doctor_id, u.name || ' ' || u.surname as name, description, photo
FROM doctors d join users u on d.user_id = u.username