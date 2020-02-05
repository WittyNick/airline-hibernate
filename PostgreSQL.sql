CREATE TABLE employees
(
    id       SERIAL,
    name     VARCHAR(30),
    surname  VARCHAR(30),
    position INTEGER,
    PRIMARY KEY (id)
);

CREATE TABLE crews
(
    id   SERIAL,
    name VARCHAR(30),
    PRIMARY KEY (id)
);

CREATE TABLE crew_employee
(
    crew_id     INTEGER NOT NULL,
    employee_id INTEGER NOT NULL,
    PRIMARY KEY (crew_id, employee_id),
    FOREIGN KEY (crew_id) REFERENCES crews (id),
    FOREIGN KEY (employee_id) REFERENCES employees (id)
);

CREATE TABLE flights
(
    id                  SERIAL,
    flight_number       INTEGER,
    start_point         VARCHAR(30),
    destination_point   VARCHAR(30),
    departure_date_time TIMESTAMP,
    arrival_date_time   TIMESTAMP,
    plane               VARCHAR(10),
    crew_id             INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (crew_id) REFERENCES crews (id)
);

