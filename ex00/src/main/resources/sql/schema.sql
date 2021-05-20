create table users
(
    id       serial      not null
        constraint users_pk
            primary key,
    username varchar(64) not null,
    password varchar(64) not null,
    lastname varchar(64),
    phone    varchar(36)
);

alter table users
    owner to postgres;

create unique index users_id_uindex
    on users (id);

create unique index users_username_uindex
    on users (username);

create table "signIn"
(
    id      serial      not null
        constraint table_name_pk
            primary key,
    date    timestamp   not null,
    ip      varchar(64) not null,
    id_user integer     not null
        constraint table_name_users_id_fk
            references users
);

alter table "signIn"
    owner to postgres;

create unique index table_name_id_uindex
    on "signIn" (id);