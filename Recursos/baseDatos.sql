-- RETO pichincha

create table Persona(
  id_persona serial4 not NULL,
  nombre varchar(100) NULL,
  genero varchar(10) null,
  fecha_nacimiento timestamp null default CURRENT_TIMESTAMP,
  identificacion varchar(50) NULL,
  direccion varchar(50) NULL,
  telefono varchar(50) NULL,

  constraint persona_pk primary key (id_persona)
 );


create table Cliente(
  id_cliente serial4 not NULL,
  id_persona serial4 not NULL,
  clave varchar(300) NULL,
  estado boolean null,
  constraint cliente_pk primary key (id_cliente)
 );
alter table Cliente add constraint id_persona_fk  FOREIGN KEY(id_persona)  REFERENCES  Persona(id_persona)

create table Cuenta(
  id_cuenta serial4 not NULL,
  id_cliente serial4 not NULL,
  numero_cuenta varchar(300) NULL,
  tipo_cuenta varchar(100) NULL,
  saldo_inicial decimal NULL,
  estado boolean null,
  constraint cuenta_pk primary key (id_cuenta)
 );
alter table Cuenta add constraint id_cliente_fk  FOREIGN KEY(id_cliente)  REFERENCES  Cliente(id_cliente)



create table Movimiento(
  id_movimiento serial4 not NULL,
  id_cuenta serial4 not NULL,
  tipo_movimiento varchar(100) NULL,
  valor decimal NULL,
  saldo decimal NULL,
  fecha_movimiento timestamp null default CURRENT_TIMESTAMP,
  constraint movimiento_pk primary key (id_movimiento)
 );
alter table Movimiento add constraint id_movimiento_fk  FOREIGN KEY(id_cuenta)  REFERENCES  Cuenta(id_cuenta)
