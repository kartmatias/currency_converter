CREATE TABLE LOGIN
(
    id SERIAL PRIMARY key,
    name VARCHAR,
    email VARCHAR,
    token VARCHAR
);

CREATE TABLE TRANSACTION
(
    id SERIAL PRIMARY KEY,
    login_id BIGINT,
    currency_input VARCHAR,
    currency_output VARCHAR,
    amount FLOAT,
    rate FLOAT,
    created_at TIMESTAMP
);

CREATE TABLE CURRENCY
(
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    rate FLOAT,
    created_at TIMESTAMP
);