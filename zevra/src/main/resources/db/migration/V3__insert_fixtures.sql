-- V3 Insert fixtures

-- 1. types
INSERT INTO types (category)
VALUES ('Cardio'),
       ('Renforcement'),
       ('Mobilité'),
       ('Etirement');

-- 2. muscles
INSERT INTO muscles (muscle_targeted, area)
VALUES ('Pectoraux', 'Haut du corps'),
       ('Dos', 'Haut du corps'),
       ('Jambes', 'Bas du corps'),
       ('Bras', 'Haut du corps'),
       ('Épaules', 'Haut du corps'),
       ('Abdominaux', 'Tronc');

-- 3. users
INSERT INTO users (firstname, lastname, username, email, password, role_id,
                   created_at, updated_at)
VALUES ('Admin', 'Zevra', 'admin', 'admin@zevra.com',
        '$2a$10$NNCxX0JUeINH9BT9d2asEufgLqUpHZrzLtKVwCDSQJWAtxNBBd5uu', 2,
        NOW(), NOW()),

       ('Alice', 'User', 'alice', 'alice@zevra.local',
        '$2a$10$FF1.eK8i4sBfV50IKQTvrOFerw5Sbo5ZkEcTmOP32hOXlQwGEtyrO', 1,
        NOW(), NOW()),

       ('Bob', 'User', 'bob', 'bob@zevra.local',
        '$2a$10$FF1.eK8i4sBfV50IKQTvrOFerw5Sbo5ZkEcTmOP32hOXlQwGEtyrO', 1,
        NOW(), NOW());

-- 4. exercices
INSERT INTO exercices (type_id, muscle_id, duration, beginner_rep,
                       intermediate_rep, hard_rep, created_at, updated_at)
VALUES (2, 3, '1970-01-01 00:10:00'::timestamp, 10, 15, 20, NOW(), NOW()),
       (2, 1, '1970-01-01 00:05:00'::timestamp, 8, 12, 20, NOW(), NOW()),
       (2, 2, '1970-01-01 00:08:00'::timestamp, 3, 8, 15, NOW(), NOW()),
       (2, 3, '1970-01-01 00:12:00'::timestamp, 8, 12, 16, NOW(), NOW()),
       (2, 6, '1970-01-01 00:03:00'::timestamp, 20, 45, 90, NOW(), NOW()),
       (2, 1, '1970-01-01 00:15:00'::timestamp, 8, 12, 15, NOW(), NOW()),
       (2, 4, '1970-01-01 00:08:00'::timestamp, 10, 15, 20, NOW(), NOW()),
       (2, 5, '1970-01-01 00:10:00'::timestamp, 10, 15, 20, NOW(), NOW()),
       (2, 6, '1970-01-01 00:08:00'::timestamp, 15, 25, 40, NOW(), NOW()),
       (1, 6, '1970-01-01 00:08:00'::timestamp, 5, 10, 15, NOW(), NOW()),
       (1, 3, '1970-01-01 00:15:00'::timestamp, 30, 60, 120, NOW(), NOW()),
       (1, 6, '1970-01-01 00:10:00'::timestamp, 20, 40, 60, NOW(), NOW()),
       (1, 3, '1970-01-01 00:05:00'::timestamp, 20, 30, 50, NOW(), NOW()),
       (3, 5, '1970-01-01 00:05:00'::timestamp, 10, 15, 20, NOW(), NOW()),
       (3, 3, '1970-01-01 00:08:00'::timestamp, 10, 15, 20, NOW(), NOW()),
       (3, 2, '1970-01-01 00:10:00'::timestamp, 5, 8, 12, NOW(), NOW()),
       (4, 1, '1970-01-01 00:05:00'::timestamp, 3, 5, 8, NOW(), NOW()),
       (4, 3, '1970-01-01 00:10:00'::timestamp, 4, 6, 10, NOW(), NOW()),
       (4, 4, '1970-01-01 00:05:00'::timestamp, 3, 5, 8, NOW(), NOW());