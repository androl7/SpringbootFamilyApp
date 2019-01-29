CREATE TABLE Family (
    family_id INT PRIMARY KEY,
    family_surname VARCHAR(45) NOT NULL,
    password VARCHAR(100) NOT NULL,
    valid BOOLEAN NOT NULL
);

CREATE TABLE Child (
    child_id INT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    surname VARCHAR(45) NOT NULL,
    PESEL VARCHAR(11) NOT NULL,
    birth_date VARCHAR(10) NOT NULL,
    gender VARCHAR(1) NOT NULL,
    family_id INT REFERENCES Family(family_id)
);

CREATE TABLE Father (
    father_id INT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    surname VARCHAR(45) NOT NULL,
    PESEL VARCHAR(11) NOT NULL,
    birth_date VARCHAR(10) NOT NULL,
    family_id INT REFERENCES Family(family_id)
);

create sequence hibernate_sequence;





