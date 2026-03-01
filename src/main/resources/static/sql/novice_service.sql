DROP TABLE IF EXISTS novice_service;
CREATE TABLE novice_service(
id INTEGER PRIMARY KEY,
service_id INTEGER,
stock INTEGER
);
INSERT INTO novice_service VALUES
(1,1,0),
(2,2,2),
(3,4,0),
(4,5,0),
(5,6,0),
(6,7,0),
(7,8,1),
(8,9,2),
(9,10,0);