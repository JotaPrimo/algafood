insert into cozinhas (nome) values ('Tailandesa');
insert into cozinhas (nome) values ('Indiana');


insert into estados (nome) values ('Minas Gerais');
insert into estados (nome) values ('São Paulo');
insert into estados (nome) values ('Ceará');

insert into cidades (nome, estado_id) values ('Uberlândia', 1);
insert into cidades (nome, estado_id) values ('Belo Horizonte', 1);
insert into cidades (nome, estado_id) values ('São Paulo', 2);
insert into cidades (nome, estado_id) values ('Campinas', 2);
insert into cidades (nome, estado_id) values ('Fortaleza', 3);

insert into forma_pagamentos (descricao) values ('Cartão de crédito');
insert into forma_pagamentos (descricao) values ('Cartão de débito');
insert into forma_pagamentos (descricao) values ('Dinheiro');

insert into permissaos (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissaos (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', 10, 1, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurantes (nome, taxa_frete, cozinha_id) values ('Thai Delivery', 9.50, 1);
insert into restaurantes (nome, taxa_frete, cozinha_id) values ('Tuk Tuk Comida Indiana', 15, 2);

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);