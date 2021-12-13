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

CREATE TABLE object(
	fileid int not null auto_increment,
	filename varchar(30),
    filetype varchar(30),
    filedata longblob,
	filesize int,
    filedate varchar(30),
    fileuri varchar(30) not null,
    fileusername varchar(30) not null,
    primary key(fileid),
	constraint `buckets` foreign key (`fileusername`,`fileuri`) references `buckets`(`username`,`uri`)
);


select * from users;
select * from buckets;
select * from object;