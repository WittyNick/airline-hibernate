CREATE TABLE flights
(
    id                SERIAL,
    flightNumber      INTEGER,
    startPoint        VARCHAR(30),
    destinationPoint  VARCHAR(30),
    departureDateTime BIGINT,
    arrivalDateTime   BIGINT,
    plane             VARCHAR(10),
    crew              INTEGER
);

CREATE TABLE crews
(
    id   SERIAL,
    name VARCHAR(30)
);

CREATE TABLE members
(
    id         SERIAL,
    crewId     INTEGER,
    employeeId INTEGER
);

CREATE TABLE employees
(
    id       SERIAL,
    name     VARCHAR(30),
    surname  VARCHAR(30),
    position INTEGER
);