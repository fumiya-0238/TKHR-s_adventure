DROP TABLE IF EXISTS beginner_service;
CREATE TABLE beginner_service(
id INTEGER PRIMARY KEY,
service_id INTEGER,
stock INTEGER
);
INSERT INTO beginner_service VALUES
(1,1,0),
(2,4,0),
(3,5,0),
(4,10,0);