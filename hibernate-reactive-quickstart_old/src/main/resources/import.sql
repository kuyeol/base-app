INSERT INTO known_fruits(id, name,emname) VALUES (1, 'Cherry','aa');
INSERT INTO known_fruits(id, name) VALUES (2, 'Apple');
INSERT INTO known_fruits(id, name) VALUES (3, 'Banana');
ALTER SEQUENCE known_fruits_id_seq RESTART WITH 4;
