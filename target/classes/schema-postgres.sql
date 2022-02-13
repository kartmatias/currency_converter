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

INSERT INTO LOGIN (name, email,token) VALUES ('Primeiro', 'primeiro@gmail.com', 'token12312312');
INSERT INTO TRANSACTION (login_id,currency_input,currency_output,amount,tax,created_at) VALUES (1,'BRL','EUR', 100,1, CURRENT_TIMESTAMP);
INSERT INTO CURRENCY (name, rate,created_at) VALUES ('TESTE', 1, CURRENT_TIMESTAMP);