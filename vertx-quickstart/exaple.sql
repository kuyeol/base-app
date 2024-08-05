-- DROP DATABASE IF EXISTS shop;

--  CREATE
-- DATABASE shop;

CREATE TABLE categories (
    category_id int NOT NULL,
    category_name varchar(70) NOT NULL,
    PRIMARY KEY (category_id)
);

CREATE TABLE books (
    book_id int NOT NULL ,
    title varchar(70) NOT NULL,
    author varchar(70) DEFAULT NULL,
    price DECIMAL NOT NULL,
    category_id int NOT NULL,
    PRIMARY KEY (book_id),
    CONSTRAINT category_id FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

CREATE TABLE orders (
   order_id bigint NOT NULL ,
   delivery_name varchar(70) NOT NULL,
   delivery_address varchar(70) NOT NULL,
   cc_name varchar(70) NOT NULL,
   cc_number varchar(32) NOT NULL,
   cc_expiry varchar(20) NOT NULL,
    PRIMARY KEY (order_id)

);

CREATE TABLE order_details (
   id bigint NOT NULL ,
   book_id int NOT NULL,
   title varchar(70) NOT NULL,
   author varchar(70) DEFAULT NULL,
   quantity int NOT NULL,
   price DECIMAL NOT NULL,
   order_id bigint NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT book_id FOREIGN KEY (book_id) REFERENCES books(book_id),
    CONSTRAINT order_id FOREIGN KEY (order_id) REFERENCES orders(order_id)
);