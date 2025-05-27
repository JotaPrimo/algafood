
CREATE TABLE pedidos (
                         id BIGSERIAL PRIMARY KEY,
                         subtotal DECIMAL(10,2) NOT NULL,
                         taxa_frete DECIMAL(10,2) NOT NULL,
                         valor_total DECIMAL(10,2) NOT NULL,

                         restaurante_id BIGINT NOT NULL,
                         usuario_cliente_id BIGINT NOT NULL,
                         forma_pagamento_id BIGINT NOT NULL,

                         endereco_cidade_id BIGINT NOT NULL,
                         endereco_cep VARCHAR(9) NOT NULL,
                         endereco_logradouro VARCHAR(100) NOT NULL,
                         endereco_numero VARCHAR(20) NOT NULL,
                         endereco_complemento VARCHAR(60),
                         endereco_bairro VARCHAR(60) NOT NULL,

                         status VARCHAR(10) NOT NULL,
                         data_criacao TIMESTAMP NOT NULL,
                         data_confirmacao TIMESTAMP,
                         data_cancelamento TIMESTAMP,
                         data_entrega TIMESTAMP,

                         CONSTRAINT fk_pedido_restaurante FOREIGN KEY (restaurante_id) REFERENCES restaurantes(id),
                         CONSTRAINT fk_pedido_usuario_cliente FOREIGN KEY (usuario_cliente_id) REFERENCES usuarios(id),
                         CONSTRAINT fk_pedido_forma_pagamento FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamentos(id)
);

CREATE TABLE item_pedido (
                             id BIGSERIAL PRIMARY KEY,
                             quantidade SMALLINT NOT NULL,
                             preco_unitario DECIMAL(10,2) NOT NULL,
                             preco_total DECIMAL(10,2) NOT NULL,
                             observacao VARCHAR(255),
                             pedido_id BIGINT NOT NULL,
                             produto_id BIGINT NOT NULL,

                             CONSTRAINT uk_item_pedido_produto UNIQUE (pedido_id, produto_id),
                             CONSTRAINT fk_item_pedido_pedido FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
                             CONSTRAINT fk_item_pedido_produto FOREIGN KEY (produto_id) REFERENCES produtos(id)
);

