-- liquibase formatted sql

-- changeset vglyva:1
create index students_name on students(name);
-- changeset vglyva:2
create index faculties_name_color on faculties(name, color);