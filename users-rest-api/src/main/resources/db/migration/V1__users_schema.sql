CREATE SCHEMA IF NOT EXISTS list;
CREATE TABLE list.t_users(
    id SERIAL PRIMARY KEY,
    c_name VARCHAR(50) NOT NULL CHECK ( length(trim(c_name))>=2 ),
    c_email VARCHAR(50),
    c_age INTEGER
);
create table list.t_category(
    id SERIAL PRIMARY KEY,
    c_name VARCHAR(50) NOT NULL
);
create table list.t_goods(
    id SERIAL PRIMARY KEY,
    c_name VARCHAR(50) NOT NULL,
    c_description VARCHAR(50),
    c_price INTEGER,
    id_category int not null
);
alter table list.t_goods
    add foreign key (id_category) references list.t_category(id)