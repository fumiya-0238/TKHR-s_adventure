DROP TABLE IF EXISTS expert_service;
CREATE TABLE expert_service(
id INTEGER PRIMARY KEY,
service_id INTEGER,
stock INTEGER
);
INSERT INTO expert_service VALUES
(1,1,0),
(2,2,2),
(3,3,1),
(4,4,0),
(5,5,0),
(6,6,0),
(7,7,0),
(8,8,1),
(9,9,4),
(10,10,0),
(11,11,0);