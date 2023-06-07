-- liquibase formatted sql
-- changeset anna:1
create table ads
(
        ad_id          serial primary key,
        user_id        int not null references users(user_id),
        title          text not null ,
        price          int not null ,
        description    text
);