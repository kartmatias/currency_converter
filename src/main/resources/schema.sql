CREATE TABLE LOGIN
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR2,
    email VARCHAR2,
    token VARCHAR2
);

CREATE TABLE TRANSACTION
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    login_id BIGINT,
    currency_input VARCHAR2,
    currency_output VARCHAR2,
    amount NUMERIC(12,2),
    tax NUMERIC(12,2),
    created_at TIMESTAMP
);

CREATE TABLE CURRENCY
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR2,
    rate DOUBLE,
    created_at TIMESTAMP
);

INSERT INTO LOGIN (name, email,token) VALUES ('Primeiro', 'primeiro@gmail.com', 'token12312312');
INSERT INTO TRANSACTION (login_id,currency_input,currency_output,amount,tax,created_at) VALUES (1,'BRL','EUR', 100,1, CURRENT_TIMESTAMP);
INSERT INTO CURRENCY (name, rate,created_at) VALUES ('TESTE', 1, CURRENT_TIMESTAMP);