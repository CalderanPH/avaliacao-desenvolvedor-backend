CREATE TABLE endereco
(
    id         UUID PRIMARY KEY,
    logradouro VARCHAR(255),
    cep        VARCHAR(255),
    numero     INT,
    cidade     VARCHAR(255)
);

CREATE TABLE pessoa
(
    id                 UUID PRIMARY KEY,
    nome               VARCHAR(255),
    data_de_nascimento DATE,
    endereco_id        UUID,
    FOREIGN KEY (endereco_id) REFERENCES endereco (id)
);
