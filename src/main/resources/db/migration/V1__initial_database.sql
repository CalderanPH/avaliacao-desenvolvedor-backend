CREATE TABLE endereco
(
    id         BIGINT PRIMARY KEY,
    logradouro VARCHAR(255),
    cep        INT,
    numero     INT,
    cidade     VARCHAR(255)
);

CREATE TABLE pessoa
(
    id                 BIGINT PRIMARY KEY,
    nome               VARCHAR(255),
    data_de_nascimento DATE,
    endereco_id        BIGINT,
    FOREIGN KEY (endereco_id) REFERENCES endereco (id)
);
