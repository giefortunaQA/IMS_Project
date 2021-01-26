INSERT INTO customers (first_name, surname, house_number, postcode) VALUES ('John', 'Smith', 1, 'AAA9 9AA');
INSERT INTO items (name,price) VALUES ('bar stool',49.99);
INSERT INTO orders (fk_cid,order_value) VALUES (1,49.99);
INSERT INTO orders_items (fk_oid,fk_iid) VALUES (1,1);
