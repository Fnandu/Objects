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
        user varchar(30) not null,
        date varchar(30),
        primary key (uri,user),
        constraint `users` foreign key (`user`) references `users`(`username`)
);

CREATE TABLE object(
	fileid int not null auto_increment,
	filename varchar(30),
    filetype varchar(30),
    filedata longblob,
	filesize varchar(10),
    filedate varchar(30),
    fileuri varchar(30) not null,
    fileusername varchar(30) not null,
    primary key(fileid),
	constraint `buckets` foreign key (`fileusername`,`fileuri`) references `buckets`(`user`,`uri`) ON DELETE CASCADE
);

CREATE TABLE versions(
	versionId int not null auto_increment,
    versionname varchar(30),
    versiondata longblob,
    versionsize varchar(10),
    versiondate varchar(30),
    versiontype varchar(30),
    versionhash varchar(50),
    fileid int not null,
    primary key(versionId),
	constraint `object` foreign key (`fileid`) references `object`(`fileid`) ON DELETE CASCADE
);