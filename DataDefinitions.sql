create database social;
use social;

create table user(
userId varchar (40) NOT null ,
email varchar(300) NOT null,
friendUserIds text,
dateOfBirth DATE,
gender char(1),
currentCity varchar(100),
sessionIds varchar(5000),
postIds text,
password varchar(200),
primary key(userId)
);

create index emailIdx on user (email);
