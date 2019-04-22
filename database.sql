create table users(id int NOT NULL auto_increment,user_name varchar(20),user_email varchar(35) not null unique key,password varchar(20)not null,address varchar(10),PRIMARY KEY (id));

CREATE TABLE book (id int NOT NULL auto_increment,book_id varchar(10) not null unique key,title varchar(500),author varchar(500),publisher varchar(1000), description varchar(2000), isbn varchar(50) not null unique key,available varchar(20),image varchar(3000), category varchar(50),owner_id int,PRIMARY KEY (id),FOREIGN KEY (owner_id) REFERENCES users(id));

create table requested_by(id int NOT NULL auto_increment,user_id int,book_id int,PRIMARY KEY (id),FOREIGN KEY (user_id) REFERENCES users(id),FOREIGN KEY (book_id) REFERENCES book(id));

create table transaction(id int NOT NULL auto_increment,txn_id int,txn_date date,book_id int,lender_id int,borrower_id int,PRIMARY KEY(id),FOREIGN KEY (txn_id) REFERENCES requested_by(id),FOREIGN KEY (book_id) REFERENCES book(id),FOREIGN KEY (lender_id) REFERENCES users(id),FOREIGN KEY (borrower_id) REFERENCES users(id));

create table requestlog(id int NOT NULL auto_increment,book_id int,lender_id int,borrower_id int,PRIMARY KEY(id),FOREIGN KEY (book_id) REFERENCES book(id),FOREIGN KEY (lender_id) REFERENCES users(id),FOREIGN KEY (borrower_id) REFERENCES users(id));
