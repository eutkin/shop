select u.username     as username,
       u.password as password,
       r.name     as role
from users u
         inner join user_roles ur on u.username = ur.username
         inner join roles r on ur.role = r.name
where u.username = ?