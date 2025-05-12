insert into cozinhas (id, nome) values (1, 'Tailandesa');
insert into cozinhas (id, nome) values (2, 'Indiana');
insert into cozinhas (id, nome) values (3, 'Argentina');
insert into cozinhas (id, nome) values (4, 'Brasileira');


insert into estados (nome) values ('Minas Gerais');
insert into estados (nome) values ('S�o Paulo');
insert into estados (nome) values ('Cear�');

insert into cidades (nome, estado_id) values ('Uberl�ndia', 1);
insert into cidades (nome, estado_id) values ('Belo Horizonte', 1);
insert into cidades (nome, estado_id) values ('S�o Paulo', 2);
insert into cidades (nome, estado_id) values ('Campinas', 2);
insert into cidades (nome, estado_id) values ('Fortaleza', 3);

insert into forma_pagamentos (descricao) values ('Cart�o de cr�dito');
insert into forma_pagamentos (descricao) values ('Cart�o de d�bito');
insert into forma_pagamentos (descricao) values ('Dinheiro');

insert into permissaos (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissaos (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurantes (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', 10, 1, TO_TIMESTAMP(1715068800000  / 1000), TO_TIMESTAMP(1715068800000  / 1000), 1, '38400-999', 'Rua Jo�o Pinheiro', '1000', 'Centro');
insert into restaurantes (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Thai Delivery',        9.50, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into restaurantes (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (3, 'Tuk Tuk Comida Indiana', 15, 2, TO_TIMESTAMP(1715068800000  / 1000), TO_TIMESTAMP(1715068800000  / 1000));
insert into restaurantes (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (4, 'Java Steakhouse', 12, 3, TO_TIMESTAMP(1715068800000  / 1000), TO_TIMESTAMP(1715068800000  / 1000));
insert into restaurantes (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (5, 'Lanchonete do Tio Sam', 11, 4, TO_TIMESTAMP(1715068800000  / 1000), TO_TIMESTAMP(1715068800000  / 1000));
insert into restaurantes (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (6, 'Bar da Maria', 6, 4, TO_TIMESTAMP(1715068800000  / 1000), TO_TIMESTAMP(1715068800000  / 1000));

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);


insert into produtos (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne su�na ao molho especial', 78.90, TRUE, 1);
insert into produtos (nome, descricao, preco, ativo, restaurante_id) values ('Camar�o tailand�s', '16 camar�es grandes ao molho picante', 110, TRUE, 1);

insert into produtos (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, TRUE, 2);

insert into produtos (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'P�o tradicional indiano com cobertura de alho', 21, TRUE, 3);
insert into produtos (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, TRUE, 3);

insert into produtos (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafil�', 79, TRUE, 4);
insert into produtos (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafil� e do outro o fil� mignon', 89, TRUE, 4);

insert into produtos (nome, descricao, preco, ativo, restaurante_id) values ('Sandu�che X-Tudo', 'Sandub�o com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, TRUE, 5);

insert into produtos (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, TRUE, 6);