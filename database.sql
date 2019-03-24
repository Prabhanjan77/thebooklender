create table user
(
  id int NOT NULL auto_increment,
  user_name varchar(20),
  user_email varchar(20) not null unique key,
  password varchar(20) not null unique key,
  address varchar(10),
  PRIMARY KEY (id)
);

CREATE TABLE book (
    id int NOT NULL auto_increment,
    book_id varchar(10) not null unique key,
    title varchar(80),
    author varchar(80),
    isbn varchar(10) not null unique key,
    category varchar(10),
    owner_id int,
    lent_to int,
    PRIMARY KEY (id),
    FOREIGN KEY (owner_id) REFERENCES user(id),
    FOREIGN KEY (lent_to) REFERENCES user(id)	
);

create table requested_by
(
   id int NOT NULL auto_increment,
   user_id int,
   book_id int,
   PRIMARY KEY (id),
   FOREIGN KEY (user_id) REFERENCES user(id),
   FOREIGN KEY (book_id) REFERENCES book(id)
);
