create database db;
use db;

create table categories
(
    category_id     int primary key auto_increment,
    category_name   varchar(50) not null unique,
    category_status bit default 1
);

create table product
(
    product_id    int primary key auto_increment,
    category_id   int         not null,
    foreign key (category_id) references categories (category_id),
    product_name  varchar(20) not null unique,
    stock         int         not null,
    cost_price    double      not null check ( cost_price > 0 ),
    selling_price double      not null check ( selling_price > 0 ),
    created_at    datetime default current_timestamp
);

