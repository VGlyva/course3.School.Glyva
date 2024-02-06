CREATE TABLE Car
(
    id    SERIAL PRIMARY KEY,
    brand TEXT,
    model TEXT,
    cost  NUMERIC(11,2)
);
CREATE TABLE Person
(
    id      SERIAL PRIMARY KEY,
    name    TEXT,
    age     INTEGER,
    license BOOLEAN,
    car_id  INTEGER REFERENCES Car (id)
);