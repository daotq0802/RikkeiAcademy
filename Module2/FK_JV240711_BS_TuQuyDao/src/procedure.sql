-- Procedure Categories

delimiter
&&
create procedure getAllCategories()
begin
select *
from db.categories;
end
&&
delimiter &&;

delimiter
&&
create procedure createCategory(category_name_in varchar (50))
begin
insert into db.categories (category_name)
values (category_name_in);
end
&&
delimiter &&;

delimiter
&&
create procedure updateCategory(category_id_in int, category_name_in varchar (50), category_status_in bit)
begin
update db.categories
set category_name   = category_name_in,
    category_status = category_status_in
where category_id = category_id_in;
end
&&
delimiter &&;

delimiter
&&
create procedure deleteCategory(category_id_in int)
begin
delete
from db.categories
where category_id = category_id_in;
end
&&
delimiter &&;

delimiter
&&
create procedure statisticProduct()
begin
select c.category_name, count(db.product.category_id) as count
from db.product join db.categories c
on c.category_id = product.category_id
group by c.category_name, product.category_id;
end
&&
delimiter &&;

-- Procedure Products
delimiter
&&
create procedure procGetProduct()
begin
select *
from db.product;
end
&&
delimiter &&;

delimiter
&&
create procedure procCreateProduct(category_id_in int, product_name_in varchar (20), stock_in int, cost_price_in double,
                                   selling_price_in double)
begin
insert into db.product(category_id, product_name, stock, cost_price, selling_price)
values (category_id_in, product_name_in, stock_in, cost_price_in, selling_price_in);
end
&&
delimiter &&

delimiter &&
create procedure procUpdateProduct(product_id_in int, category_id_in int, product_name_in varchar (20), stock_in int,
                                   cost_price_in double, selling_price_in double, created_at_in datetime)
begin
update db.product
set category_id   = category_id_in,
    product_name  = product_name_in,
    stock         = stock_in,
    cost_price    = cost_price_in,
    selling_price =selling_price_in,
    created_at    = created_at_in
where product_id = product_id_in;
end
&&
delimiter &&;

delimiter
&&
create procedure procDeleteProduct(product_id_in int)
begin
delete
from db.product
where product_id = product_id_in;
end
&&
delimiter &&;

delimiter
&&
create procedure procCreatedDateDESC()
begin
select *
from db.product
order by created_at DESC;
end
&&
delimiter &&;

delimiter
&&
create procedure procSearchBySellingPrice(selling_price_start_in double, selling_price_end_in double)
begin
select product_name, selling_price
from db.product
where selling_price >= selling_price_start_in
  and selling_price <= selling_price_end_in;
end
&&
delimiter &&

delimiter &&
create procedure procGetTop3Product()
begin
select product_name, (selling_price - cost_price) as profit
from db.product
order by profit DESC limit 3;
end
&&
delimiter &&;