create database BookManagament;
use bookmanagament;
create table BookType(book_type_id int primary key auto_increment,
						book_type_name varchar(100) not null unique, 
						book_type_description text, 
						book_type_status bit);
create table Books(
book_id char(4) primary key, 
book_name varchar(200) not null unique,
book_price float check(book_price>0),
created date,
book_content text not null,
publisher varchar(150) not null,
no_of_pages int not null,
author varchar(100),
book_type_id int not null,
book_status bit);
alter table Books add constraint foreign key (book_type_id) references booktype(book_type_id);
insert into booktype(book_type_name,book_type_description,book_type_status)
values('type 3','des 3', 1);
insert into booktype(book_type_name,book_type_description,book_type_status)
values('type 4','des 4', 1);
insert into booktype(book_type_name,book_type_description,book_type_status)
values('type 5','des 5', 1);
insert into booktype(book_type_name,book_type_description,book_type_status)
values('type 6','des 6', 1);
insert into booktype(book_type_name,book_type_description,book_type_status)
values('type 7','des 7', 1);
insert into booktype(book_type_name,book_type_description,book_type_status)
values('type 8','des 8', 1);
insert into booktype(book_type_name,book_type_description,book_type_status)
values('type 9','des 9', 1);
insert into booktype(book_type_name,book_type_description,book_type_status)
values('type 10','des 10', 1);
select * from booktype;

insert into books(book_id,book_name,book_price,created,book_content,publisher,no_of_pages,author,book_type_id,book_status)
values ('B001','book 1', 2000, '2024-10-19','content 1','nha sach', 20,'author1',2,1);
insert into books(book_id,book_name,book_price,created,book_content,publisher,no_of_pages,author,book_type_id,book_status)
values ('B002','book 2', 2500, '2024-9-10','content 1','nha sach 1', 24,'author1',2,1);
insert into books(book_id,book_name,book_price,created,book_content,publisher,no_of_pages,author,book_type_id,book_status)
values ('B003','book 3', 4900, '2024-5-22','content 1','nha sach 2', 50,'author2',4,1);
insert into books(book_id,book_name,book_price,created,book_content,publisher,no_of_pages,author,book_type_id,book_status)
values ('B004','book 4', 5000, '2024-3-30','content 1','nha sach 1', 66,'author3',5,1);
insert into books(book_id,book_name,book_price,created,book_content,publisher,no_of_pages,author,book_type_id,book_status)
values ('B005','book 5', 7000, '2024-8-11','content 1','nha sach 2', 65,'author3',5,1);
insert into books(book_id,book_name,book_price,created,book_content,publisher,no_of_pages,author,book_type_id,book_status)
values ('B006','book 6', 3599, '2024-2-8','content 1','nha sach 2', 45,'author4',5,1);
insert into books(book_id,book_name,book_price,created,book_content,publisher,no_of_pages,author,book_type_id,book_status)
values ('B007','book 7', 4999, '2024-10-28','content 1','nha sach 3', 34,'author4',7,1);
insert into books(book_id,book_name,book_price,created,book_content,publisher,no_of_pages,author,book_type_id,book_status)
values ('B008','book 8', 5499, '2024-5-8','content 1','nha sach 4', 100,'author6',8,1);
insert into books(book_id,book_name,book_price,created,book_content,publisher,no_of_pages,author,book_type_id,book_status)
values ('B009','book 9', 9876, '2024-8-19','content 1','nha sach 5', 167,'author5',10,1);
insert into books(book_id,book_name,book_price,created,book_content,publisher,no_of_pages,author,book_type_id,book_status)
values ('B010','book 10', 10000, '2023-12-12','content 1','nha sach 7', 276,'author8',11,1);

select * from books;

update books set book_status = 0 where book_id = 'b001';

delimiter &&
create procedure getAllBookTypeActive()
begin
select * from booktype where boo_type_status = 1;
end &&
delimiter &&;
call getAllBookTypeActive();

delimiter &&
create procedure addNewBookType(name_in varchar(100), des_in text, status_in bit)
begin
	insert into booktype(book_type_name,book_type_description,book_type_status)
	values(name_in,des_in,status_in);
end &&
delimiter &&;
call addNewBookType('type test', 'des test',0)

delimiter &&
create procedure updateBookType(id_in int, name_in varchar(100), des_in text, status_in bit)
begin
	update booktype
    set book_type_name = name_in,
		book_type_description = des_in,
		book_type_status = status_in 
	where book_type_id = id_in;
end &&
delimiter &&;
call updateBookType(2,'type updated','des updated',0)

delimiter &&
create procedure deleteBookType(id_in int)
begin
	declare countBook int;
	set countBook = (select book_type_id from books where book_type_id = id_in);
	if(countBook = 0) 
then delete from booktype where book_type_id = id_in;
	end if;
end &&
delimiter &&;
call deleteBookType(6)

delimiter &&
create procedure getBookInfoWithBookTypeName()
begin
	select *, t.book_type_name from books b join booktype t on t.book_type_id = b.book_type_id;
end&&
delimiter &&;
call getBookInfoWithBookTypeName()

delimiter &&
create procedure addNewBook(
book_id_in char(4), 
book_name_in varchar(200),
book_price_in float,
created_in date,
book_content_in text,
publisher_in varchar(150),
no_of_pages_in int,
author_in varchar(100),
book_type_id_in int,
book_status_in bit)
begin
	insert into books(book_id,book_name,book_price,created,book_content,publisher,no_of_pages,author,book_type_id,book_status)
values (book_id_in, book_name_in,book_price_in,created_in,book_content_in,publisher_in,no_of_pages_in,author_in,book_type_id_in,book_status_in);
end &&
delimiter &&;
call addNewBook('B006','book 6', 17000, '2023-12-12','content 6','nha sach 6', 276,'author8',333,0)

delimiter &&
create procedure updateBooks(id_in char(4), book_name_in varchar(200),
book_price_in float,
created_in date,
book_content_in text,
publisher_in varchar(150),
no_of_pages_in int,
author_in varchar(100),
book_type_id_in int,
book_status_in bit)
begin
	update booktype
    set 
		book_name = book_name_in,
		book_price  = book_price_in,
		created = created_in,
		book_content  = book_content_in,
		publisher = book_publisher_in,
		no_of_pages  = no_of_pages_in,
		author = author_in,
		book_type_id = book_type_id_in,
		book_status= book_status_in
	where book_type_id = id_in;
end &&
delimiter &&;
call updateBooks('B006','book 6', 17077, '2023-12-12','content 6','nha sach 6', 276,'author8',333,0)

delimiter &&
create procedure deleteBooks(id_in char(4))
begin
	delete from books where book_id = id_in;
end &&
delimiter &&;
call deleteBooks('B006');

delimiter &&
create procedure countBookByType()
begin
	select book_type_id as 'Book Type', count(book_type_id) as 'Number of Book' from books group by book_type_id;
end &&
delimiter &&;
call countBookByType();



