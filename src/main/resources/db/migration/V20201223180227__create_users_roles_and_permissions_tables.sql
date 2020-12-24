CREATE TABLE user (
    id bigint not null auto_increment primary key,
    name varchar(100) not null,
    username varchar(50) not null unique,
    email varchar(50) unique,
    password varchar(255) not null,
    enabled boolean default true
);

CREATE TABLE role (
    id bigint not null auto_increment primary key,
    name varchar(100) not null,
    title varchar(100),
    enabled bool default true
);

CREATE TABLE permission (
    id bigint not null auto_increment primary key,
    name varchar(100) not null,
    enabled bool default true
);

CREATE TABLE role_user (
    role_id bigint not null,
    user_id bigint not null,
    constraint fk_role_user_role foreign key (role_id) references role(id) on delete cascade,
    constraint fk_role_user_user foreign key (user_id) references user(id) on delete cascade
);

CREATE TABLE permission_role (
    permission_id bigint not null,
    role_id bigint not null,
    constraint fk_permission_role_permission foreign key (permission_id) references permission(id) on delete cascade,
    constraint fk_permission_role_role foreign key (role_id) references role(id) on delete cascade
);

/*Unique Keys*/
create unique index unique_role_user_role_id_user_id on role_user (user_id, role_id);
create unique index unique_permission_role_permission_id_role_id on permission_role (permission_id, role_id);