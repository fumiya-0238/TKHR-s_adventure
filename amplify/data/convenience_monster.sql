DROP TABLE IF EXISTS convenience_monster;
CREATE TABLE convenience_monster(
id INTEGER PRIMARY KEY,
monster_id INTEGER,
monster_lv INTEGER
);
INSERT INTO convenience_monster VALUES
(1,1,1),
(2,2,1),
(3,3,1),
(4,3,1),
(5,3,1),
(6,4,1),
(7,4,1),
(8,5,1),
(9,5,1),
(10,6,1);