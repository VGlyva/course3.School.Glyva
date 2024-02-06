SELECT s.name s.age, f.name FROM Students s;
LEFT JOIN Faculties f ON s.faculty.id = s.id;

SELECT s.name FROM Avatar a
INNER JOIN Students s ON a.students_id = s.id;