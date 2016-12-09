create database test_120616

go

use test_120616

go

create table Product(
	id int identity primary key,
	name nvarchar(100) not null,
	category nvarchar(50), 
	unit nvarchar(10) not null
)

go

select * from Product

go

update Product set category = N'Sữa đặc', name = N'Sữa đặc vỉ', unit = N'Chai' where id = 1

go

delete from Product where id = 2

go


select top 1 from Product where id


create table SalesOrder(
	id int primary key identity,
	customer_name nvarchar(100) not null,
	customer_phone nvarchar(20),
	customer_email nvarchar(50)
)

go

select * from SalesOrder