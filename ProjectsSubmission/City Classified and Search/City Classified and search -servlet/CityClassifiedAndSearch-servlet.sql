create database CitySearch;
use CitySearch;
create table user(
userid int not null auto_increment,
username varchar(20),
useremail varchar(30),
password varchar(15),
mobile varchar(10),
useraddress varchar(30),
usercity varchar(10),
role varchar(10),
enabled boolean,
primary key(userid)
);
create table classified(
classifiedid int not null auto_increment,
userid int,
classifiedtitle varchar(20),
description varchar(70),
classifiedcategory varchar(20),
primary key (classifiedid),
foreign key (userid) references user(userid)
);
create table citydetails(
cityid int not null auto_increment,
userid int,
city varchar(10),
category varchar(20),
name varchar(20),
address varchar(30),
link varchar(1000),
primary key(cityid),
foreign key(userid) references user(userid)
);