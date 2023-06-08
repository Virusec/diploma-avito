-- liquibase formatted sql
-- changeset anna:1
create table users
(
    user_id    serial primary key,
    password   varchar(100) not null,
    email      varchar(255) not null unique ,
    first_name varchar(30)  not null,
    last_name  varchar(30)  not null,
    phone      varchar(12)  not null,
    role       varchar(50)  not null
);

-- changeset anna:2
create table ads
(
    ad_id          serial primary key,
    user_id        int not null references users(user_id) on delete cascade,
    title          text not null ,
    price          int not null ,
    description    text
);

-- changeset anna:3
create table comments
(
    comment_id     serial primary key,
    user_id        int not null references users(user_id),
    created_at     timestamptz not null ,
    comments_text  text,
    ad_id int not null references ads (ad_id)
);
