create database checkout;

use checkout;

create table users(
	userid char(36) primary key,
    firstname varchar(25),
    lastname varchar(25),
    mail varchar(50),
    phone varchar(20)
);

create table useraddress(
    addressid int auto_increment primary key,
    userid char(36),
    addressname varchar(24),
    state varchar(20),
    city varchar(50),
    housenumber varchar(10),
    addressdetails varchar(50),
    enabled bool
);

create table userpayment(
	paymentid int auto_increment primary key,
    userid char(36),
    paymentname varchar(10),
    ownername varchar(50),
    cardnumber char(64),
    expiration char(64),
    cvv char(64),
    enabled bool
);

create table checkout(
	checkoutuuid char(36) primary key,
    userid char(36),
    addressid int,
    paymentid int,
    totalamount decimal(18,2),
    checkoutstatus varchar(10)
);

create table product(
	productid int auto_increment primary key,
    productname varchar(50),
    stock int,
    unitprice decimal(18,2),
    enabled bool 
);

create table productorder(
	orderid int auto_increment primary key,
    checkoutuuid char(36),
    productid int,
    quantity int,
    unitprice decimal(18,2),
    totalamount decimal(18,2)
);

