-- V1 Init - Creation of the tables

-- 1. roles
CREATE TABLE roles (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    permission VARCHAR(50) NOT NULL UNIQUE
);

-- 2. types
CREATE TABLE types (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    category VARCHAR(50) NOT NULL
);

-- 3.muscles
CREATE TABLE muscles (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    muscle_targeted VARCHAR(50) NOT NULL,
    area VARCHAR(50) NOT NULL
);

-- 4. user
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role_id BIGINT NOT NULL REFERENCES roles(id),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

-- 5. exercices
CREATE TABLE exercices (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    type_id BIGINT NOT NULL REFERENCES types(id),
    muscle_id BIGINT NOT NULL REFERENCES muscles(id),
    duration TIMESTAMP NOT NULL,
    beginner_rep INTEGER,
    intermediate_rep INTEGER,
    hard_rep INTEGER,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

-- 6. favorites
CREATE TABLE favorites (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255) NOT NULL,
    user_id UUID NOT NULL REFERENCES users(id),
    exercice_id BIGINT NOT NULL REFERENCES exercices(id),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

-- 7. training_lists
CREATE TABLE training_lists (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    user_id UUID NOT NULL REFERENCES users(id),
    exercice_id BIGINT NOT NULL REFERENCES exercices(id),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);