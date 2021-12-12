DROP DATABASE IF EXISTS objects;
CREATE DATABASE IF NOT EXISTS objects;
USE objects;

CREATE TABLE users(
		username varchar(30) not null,
		password varchar(60),
		primary key (username)
);

CREATE TABLE buckets(
		uri varchar(30) not null,
        username varchar(30) not null,
        date varchar(30),
        primary key (uri,username),
        constraint `users` foreign key (`username`) references `users`(`username`)
);

select * from users;
select * from buckets;