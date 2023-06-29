-- liquibase formatted sql
-- changeset anna:1
insert into users(password, email, first_name, last_name, phone, role, image_id)
values ('$2a$10$V1zJ04Or3IwatD25TuCab.NMVb8Lo5etOyPZqKMKYPdcS2A1AT1be',
        'superadmin@adm.ru',
        'Владимир',
        'Ульянов',
        '+78005553535',
        'ADMIN',
         null)