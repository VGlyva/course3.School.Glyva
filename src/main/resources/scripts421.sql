ALTER TABLE Students ALTER COLUMN age CHECK (age > 15);
ALTER TABLE Students ALTER COLUMN name SET NOT NULL;
ALTER TABLE Students ADD CONSTRAINT name_unique UNIQUE (name);
ALTER TABLE Faculties ADD CONSTRAINT name_color_unique UNIQUE (name, color);
ALTER TABLE Students ADD CONSTRAINT age_default DEFAULT 20 FOR age;