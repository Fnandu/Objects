DROP DATABASE IF EXISTS objects;
CREATE DATABASE IF NOT EXISTS objects;
USE objects;

CREATE TABLE users(
                      username varchar(30) not null,
                      password varchar(60),
                      primary key (username)
);

insert into users values('liceu','1234');
select * from users;