CREATE TABLE forma_pagamentos (
                                  id BIGSERIAL PRIMARY KEY,
                                  descricao VARCHAR(60) NOT NULL
);

CREATE TABLE grupos (
                        id BIGSERIAL PRIMARY KEY,
                        nome VARCHAR(60) NOT NULL
);

CREATE TABLE grupo_permissao (
                                 grupo_id BIGINT NOT NULL,
                                 permissao_id BIGINT NOT NULL,
                                 PRIMARY KEY (grupo_id, permissao_id)
);

CREATE TABLE permissaos (
                            id BIGSERIAL PRIMARY KEY,
                            descricao VARCHAR(60) NOT NULL,
                            nome VARCHAR(100) NOT NULL
);

CREATE TABLE produtos (
                          id BIGSERIAL PRIMARY KEY,
                          restaurante_id BIGINT NOT NULL,
                          nome VARCHAR(80) NOT NULL,
                          descricao TEXT NOT NULL,
                          preco DECIMAL(10,2) NOT NULL,
                          ativo BOOLEAN NOT NULL
);

CREATE TABLE restaurantes (
                              id BIGSERIAL PRIMARY KEY,
                              cozinha_id BIGINT NOT NULL,
                              nome VARCHAR(80) NOT NULL,
                              taxa_frete DECIMAL(10,2) NOT NULL,
                              data_atualizacao TIMESTAMP NOT NULL,
                              data_cadastro TIMESTAMP NOT NULL,
                              endereco_cidade_id BIGINT,
                              endereco_cep VARCHAR(9),
                              endereco_logradouro VARCHAR(100),
                              endereco_numero VARCHAR(20),
                              endereco_complemento VARCHAR(60),
                              endereco_bairro VARCHAR(60)
);

CREATE TABLE restaurante_forma_pagamento (
                                             restaurante_id BIGINT NOT NULL,
                                             forma_pagamento_id BIGINT NOT NULL,
                                             PRIMARY KEY (restaurante_id, forma_pagamento_id)
);

CREATE TABLE usuarios (
                          id BIGSERIAL PRIMARY KEY,
                          nome VARCHAR(80) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          senha VARCHAR(255) NOT NULL,
                          data_cadastro TIMESTAMP NOT NULL
);

CREATE TABLE usuario_grupo (
                               usuario_id BIGINT NOT NULL,
                               grupo_id BIGINT NOT NULL,
                               PRIMARY KEY (usuario_id, grupo_id)
);

-- Foreign Key Constraints
ALTER TABLE grupo_permissao
    ADD CONSTRAINT fk_grupo_permissao_permissao
        FOREIGN KEY (permissao_id) REFERENCES permissaos (id);

ALTER TABLE grupo_permissao
    ADD CONSTRAINT fk_grupo_permissao_grupo
        FOREIGN KEY (grupo_id) REFERENCES grupos (id);

ALTER TABLE produtos
    ADD CONSTRAINT fk_produto_restaurante
        FOREIGN KEY (restaurante_id) REFERENCES restaurantes (id);

ALTER TABLE restaurantes
    ADD CONSTRAINT fk_restaurante_cozinha
        FOREIGN KEY (cozinha_id) REFERENCES cozinhas (id);

ALTER TABLE restaurantes
    ADD CONSTRAINT fk_restaurante_cidade
        FOREIGN KEY (endereco_cidade_id) REFERENCES cidades (id);

ALTER TABLE restaurante_forma_pagamento
    ADD CONSTRAINT fk_rest_forma_pagto_forma_pagto
        FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamentos (id);

ALTER TABLE restaurante_forma_pagamento
    ADD CONSTRAINT fk_rest_forma_pagto_restaurante
        FOREIGN KEY (restaurante_id) REFERENCES restaurantes (id);

ALTER TABLE usuario_grupo
    ADD CONSTRAINT fk_usuario_grupo_grupo
        FOREIGN KEY (grupo_id) REFERENCES grupos (id);

ALTER TABLE usuario_grupo
    ADD CONSTRAINT fk_usuario_grupo_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuarios (id);