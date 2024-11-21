CREATE TABLE cartorioteste.cartorio (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    observacao VARCHAR(250),
    situacao_cartorio_id VARCHAR(20) NOT NULL,
    CONSTRAINT fk_situacao FOREIGN KEY (situacao_cartorio_id) REFERENCES situacao_cartorio(id));

CREATE TABLE cartorioteste.cartorio_atribuicoes (
    cartorio_id INT NOT NULL,
    atribuicao_id VARCHAR(20) NOT NULL,
    PRIMARY KEY (cartorio_id, atribuicao_id),
    FOREIGN KEY (cartorio_id) REFERENCES cartorio(id),
    FOREIGN KEY (atribuicao_id) REFERENCES atribuicao_cartorio(id));
