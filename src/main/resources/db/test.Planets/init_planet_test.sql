CREATE TABLE IF NOT EXISTS PLANETS (
    id int not null,
    name varchar not null unique,
    weight int,
    radius int
);

INSERT INTO PLANETS VALUES (1, 'first', 100000, 10000);
INSERT INTO PLANETS VALUES (2, 'second', 200000, 20000);
INSERT INTO PLANETS VALUES (3, 'third', 300000, 30000);