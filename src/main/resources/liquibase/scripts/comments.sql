-- liquibase formatted sql
-- changeset anna:1
create table comments
(
        comment_id     serial primary key,
        user_id        int not null references users(user_id),
        created_at     bigint not null ,
        comments_text  text
);

-- changeset anna:2
alter table comments
    add column ad_id int not null references ads (ad_id);
