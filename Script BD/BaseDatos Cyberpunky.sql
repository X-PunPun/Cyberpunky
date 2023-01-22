new database db_ciberpunky;

use db_ciberpunky;
create table usuario(
idUser int(11)  auto_increment primary key,
usuario varchar(20) not null,
contraseña varchar(20)not null
);

Create table PersoDB(
Id int (11) auto_increment,
Apodo varchar(20) not null,
Clase varchar(20) not null,
Reflejo int(2) not null,
Habilidada int(2) not null,
Tc int(2) not null,
Humanidad int(11) not null,
primary key (Id));


delete from persodb where id="14";
select * from persodb;
select * from usuario;
