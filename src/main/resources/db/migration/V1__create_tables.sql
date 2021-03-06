drop table if exists captcha_codes;
drop table if exists global_settings;
drop table if exists post_comments;
drop table if exists post_votes;
drop table if exists posts;
drop table if exists tag2post;
drop table if exists tags;
drop table if exists users;


create table captcha_codes
(
    id          integer  not null auto_increment,
    code        TINYTEXT not null,
    secret_code TINYTEXT not null,
    time        DATETIME not null,
    primary key (id)
);

create table global_settings
(
    id    integer      not null auto_increment,
    code  varchar(255) not null,
    name  varchar(255) not null,
    value varchar(255) not null,
    primary key (id)
);

create table post_comments
(
    id        integer not null auto_increment,
    text      TEXT    not null,
    time      datetime(6) not null,
    parent_id integer,
    post_id   integer not null,
    user_id   integer not null,
    primary key (id)
);

create table post_votes
(
    id      integer not null auto_increment,
    time    datetime(6) not null,
    value   TINYINT not null,
    post_id integer not null,
    user_id integer not null,
    primary key (id)
);

create table posts
(
    id                integer      not null auto_increment,
    date_time         datetime(6) not null,
    is_active         TINYINT      not null,
    moderation_status varchar(255) not null,
    text              TEXT         not null,
    title             varchar(255) not null,
    view_count        integer      not null,
    moderator_id      integer,
    user_id           integer      not null,
    primary key (id)
);

create table tag2post
(
    id      integer not null auto_increment,
    tag_id  integer not null,
    post_id integer not null,
    primary key (id)
);

create table tags
(
    id   integer      not null auto_increment,
    name varchar(255) not null,
    primary key (id)
);

create table users
(
    id           integer      not null auto_increment,
    code         varchar(255),
    email        varchar(255) not null,
    is_moderator TINYINT      not null,
    name         varchar(255) not null,
    password     varchar(255) not null,
    photo        varchar(255),
    reg_time     datetime(6) not null,
    primary key (id)
);