alter table  cidades drop column nome_estado;
alter table cidades rename column nome_cidade to nome;

alter table cidades add column estado_id bigint not null;

alter table cidades add constraint fk_cidade_estado
    foreign key (estado_id)  references estados (id);