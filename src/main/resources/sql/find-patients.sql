SELECT name, surname, birth_date
FROM patients
    JOIN users ON patients.user_id = users.username