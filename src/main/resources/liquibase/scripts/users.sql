-- liquibase formatted sql
-- changeset anna:1
create table users
(
    user_id    serial primary key,
    password   varchar(100) not null,
    email      varchar(255) not null,
    first_name varchar(30)  not null,
    last_name  varchar(30)  not null,
    phone      varchar(12)  not null,
    role       varchar(50)  not null
);

-- changeset anna:2
alter table users
    add constraint email_unique unique (email);