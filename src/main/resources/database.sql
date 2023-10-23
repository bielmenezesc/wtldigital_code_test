CREATE DATABASE wlt_digital_code_test;

USE wlt_digital_code_test;

CREATE TABLE pessoa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL,
    password VARCHAR(255) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    estado VARCHAR(2),
    UNIQUE KEY (cpf)
);

CREATE TABLE automovel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(255) UNIQUE,
    veiculo VARCHAR(255),
    pessoa_id INT,
    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
);