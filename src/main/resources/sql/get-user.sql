select u.name     as username,
       u.password as password,
       r.name     as role
from users u
         inner join user_roles ur on u.name = ur.username
         inner join roles r on ur.role = r.name
where u.name = ?