-- CREATE TABLE SUPERHERO
CREATE TABLE IF NOT EXISTS SUPERHEROES_SCHEMA.SUPERHERO (
 	id bigint NOT NULL AUTO_INCREMENT,
 	name varchar(50) NOT NULL DEFAULT 'Unassigned',
 	vigor smallint NOT NULL DEFAULT 0,
 	mind smallint NOT NULL DEFAULT 0,
 	endurance smallint NOT NULL DEFAULT 0,
 	strength smallint NOT NULL DEFAULT 0,
 	dexterity smallint NOT NULL DEFAULT 0,
 	intelligence smallint NOT NULL DEFAULT 0,
 	speed smallint NOT NULL DEFAULT 0,
 	is_active boolean NOT NULL DEFAULT true,
 	created_at timestamp not null default now()::timestamp,
    modified_at timestamp not null default now()::timestamp,
 	CONSTRAINT superhero_pk PRIMARY KEY (id),
    CONSTRAINT superhero_un_name UNIQUE (name)
);

-- INSERT INTO SUPERHERO
INSERT INTO SUPERHEROES_SCHEMA.SUPERHERO
    (name, vigor, mind, endurance, strength, dexterity, intelligence, speed, is_active, created_at, modified_at)
VALUES
    ('Superman', 90, 30, 80, 85, 60, 50, 80, true, now()::timestamp, now()::timestamp),
    ('Hulk', 100, 10, 90, 95, 12, 04, 50, true, now()::timestamp, now()::timestamp),
    ('Spiderman', 60, 70, 55, 70, 90, 70, 60, true, now()::timestamp, now()::timestamp),
    ('Ironman', 50, 60, 50, 50, 80, 95, 70, true, now()::timestamp, now()::timestamp),
    ('Captain America', 75, 35, 80, 65, 70, 60, 55, true, now()::timestamp, now()::timestamp),
    ('Batman', 30, 90, 50, 40, 80, 100, 20, true, now()::timestamp, now()::timestamp),
    ('Deadpool', 100, 00, 100, 50, 80, 00, 30, true, now()::timestamp, now()::timestamp),
    ('Aquaman', 55, 55, 70, 70, 67, 80, 50, true, now()::timestamp, now()::timestamp),
    ('Black Widow', 25, 60, 30, 50, 89, 63, 12, true, now()::timestamp, now()::timestamp),
    ('Black Panther', 60, 67, 82, 85, 85, 64, 50, true, now()::timestamp, now()::timestamp),
    ('Thor', 95, 40, 90, 95, 86, 05, 45, true, now()::timestamp, now()::timestamp),
    ('Doctor Strange', 35, 100, 25, 15, 90, 100, 100, true, now()::timestamp, now()::timestamp),
    ('Flash', 43, 20, 100, 35, 50, 20, 100, true, now()::timestamp, now()::timestamp),
    ('Rocket', 15, 69, 50, 30, 30, 100, 34, true, now()::timestamp, now()::timestamp),
    ('One Punchman', 100, 100, 100, 100, 100, 100, 100, true, now()::timestamp, now()::timestamp),
    ('Wonder Woman', 85, 70, 90, 90, 90, 85, 40, true, now()::timestamp, now()::timestamp),
    ('Wolverine', 100, 30, 100, 60, 70, 25, 20, true, now()::timestamp, now()::timestamp),
    ('Ant-man', 30, 20, 30, 20, 45, 30, 26, true, now()::timestamp, now()::timestamp),
    ('Scarlet Witch', 70, 100, 35, 15, 85, 100, 35, true, now()::timestamp, now()::timestamp),
    ('Daredevil', 50, 60, 50, 50, 50, 70, 35, true, now()::timestamp, now()::timestamp),
    ('Cyborg', 55, 76, 50, 50, 76, 90, 30, true, now()::timestamp, now()::timestamp);