drop table if exists TB_CLIENTE;
create table TB_CLIENTE(
 id long not null auto_increment primary key,
 nome varchar(255) not null,
 cpf varchar (20) not null,
 email varchar(200) not null,
 numero varchar(5) not null,
 rua varchar(200) not null,
 cidade varchar(100) not null,
 estado varchar(100) not null
);