/* Replace with your SQL commands */
create table if not exists public.authors
(
    id serial primary key,
    first_name   varchar(16) not null,
    last_name  varchar(128)
);

insert into authors(first_name, last_name) values('nombre1','ape1');
insert into authors(first_name, last_name) values('nombre2','ape2');
insert into authors(first_name, last_name) values('nombre3','ape3');
insert into authors(first_name, last_name) values('nombre4','ape4');
