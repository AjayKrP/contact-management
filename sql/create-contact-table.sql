CREATE DATABASE contacts;
USE contacts;

create table contacts (
	id  int(3) NOT NULL AUTO_INCREMENT,
	name varchar(120) NOT NULL,
	email varchar(220) NOT NULL,
	address varchar(255),
	mobile varchar(11),
	PRIMARY KEY (id)
);

