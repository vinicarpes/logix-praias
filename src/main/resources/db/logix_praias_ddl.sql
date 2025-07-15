CREATE TABLE categoria(
	id int not null auto_increment,
    nome varchar(200) not null unique,
    descricao text,
    primary key(id)
)engine =innodb;

CREATE TABLE material(
	id int not null auto_increment,
    categoria_id int not null,
    nome varchar(200) not null unique,
    descricao text,
    primary key(id),
    foreign key(categoria_id) references categoria(id)
)engine = innodb;
    

CREATE TABLE estoque(
	id int not null auto_increment,
    material_id int not null,
    quantidade int not null default (0),
    primary key(id),
    constraint fk_estoque foreign key (material_id) references material(id)
) engine = innodb;

CREATE TABLE usuario(
	id int not null auto_increment,
    nome varchar(200) not null,
    email varchar(255) not null unique,
    telefone varchar(20) not null unique,
    data_cadastro date,
    graduacao enum('SD','CB','SGT','ST','TEN','CAP','MAJ','TCEL','CEL','GVC') not null,
    mtcl varchar(20) not null unique,
	perfil enum('ADMINISTRADOR','BOMBEIRO_MILITAR','GUARDA_VIDAS_CIVIL') not null,
	setor enum('BBM_1','BBM_1_2','BBM_1_2_1','BBM_1_2_2','BBM_1_3_1','BBM_1_3','CEBM') not null,
	primary key(id)
)engine = innodb;

create table retirada_material(
	id int not null auto_increment,
    quantidade int not null,
    observacao text, 
    data_retirada datetime,
    usuario_id int,
    material_id int not null,
    primary key(id),
    foreign key(material_id) references material(id)
)engine = innodb;

create table recebimento_material(
	id int not null auto_increment,
    quantidade int not null,
    descricao text, 
    data_recebimento datetime,
    fornecedor varchar(100),
    usuario_id int,
    material_id int not null,
    primary key(id),
    foreign key(material_id) references material(id)
)engine = innodb;