DROP TABLE BOOK;

CREATE TABLE Book (
    bid         int   GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) not null,
    title       VARCHAR(60)  NOT NULL,
    price       double NOT NULL,
    author      varchar(50)   not null,
    
   category varchar(20) CONSTRAINT category_ck CHECK (category IN ('Fiction', 'Science', 'Engineering', 'Maths','Modern','Kids')) NOT NULL,
    picture     varchar(1000)    not null,
    description varchar(10000)     not null default 'Welcome to Book World!',
    sold int  default 0,
    PRIMARY KEY (bid),
    constraint sold check ( sold >= 0 )
);

INSERT INTO Book ( title, price, author, category, picture) VALUES ( 'Educated', ' 5.99', ' Tara Westover',  'Fiction', 'assets/book-cover-1.jpg');

INSERT INTO Book ( title, price, author, category, picture, sold) VALUES ( 'Dune', ' 59.99', ' Frank Herbert',  'Fiction', 'assets/book-cover-10.jpg', '2');

INSERT INTO Book ( title, price, author, category, picture, description, sold) VALUES ( 'No Place Like Here', 21.99, 'Christina June', 'Science', 'assets/book-cover-12.jpg', 'def',  0);
INSERT INTO Book ( title, price, author, category, picture, description, sold) VALUES ( 'Dog Flowers', 12.99, 'Danielle Geller', 'Fiction', 'assets/book-cover-8.jpg', 'def', 0);
INSERT INTO Book ( title, price, author, category, picture, description, sold)  VALUES ( 'Harry Potter', 8.99, 'J.K Rowling', 'Kids', 'assets/book-cover-13.jpg', 'def', 0);
INSERT INTO Book ( title, price, author, category, picture, description, sold)  VALUES ( 'The Outsider', 8.99, 'Stephen King', 'Fiction', 'assets/book-cover-9.jpg', 'def', 0);

INSERT INTO Book (title, price, author, category, picture, description, sold)  VALUES ('Loner', '10.99', 'Georgina Young', 'Modern', 'assets/book-cover-4.jpg',  'def', 0);




DROP TABLE USER;
DROP TABLE Address;

CREATE TABLE User
(    id  int  unique  GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) not null,
    fName    varchar(10) not null,
    lName    varchar(20) not null,
    email    varchar(30) not null,
    password varchar(20) not null,
    userType varchar(20) CONSTRAINT userType_ck CHECK (userType IN ('Costumer','Administrator','Partner')) NOT NULL Default 'Costumer',
    primary key (id, fName, email)
);


INSERT INTO User (fName, lName, email, password) VALUES ('John', 'Oliver', 'johnoli@Canada.com', 'okey');
INSERT INTO User (fName, lName, email, password, userType) VALUES ('Kyle', 'Lee', 'joe@gmail.com', 'okey', 'Administrator');
INSERT INTO User (fName, lName, email, password, userType) VALUES ('Areeba', 'Arbidi', '12333@gmail.com', 'okey', 'Partner');



CREATE TABLE Address
(
    id       INT GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) not null,
    uId      int references  User(id),
    street   VARCHAR(100) NOT NULL,
    province VARCHAR(20)  NOT NULL,
    country  VARCHAR(20)  NOT NULL,
    zip      VARCHAR(20)  NOT NULL,
    phone    VARCHAR(20),
    PRIMARY KEY (id)
);

INSERT INTO Address ( street, province, country, zip, phone)
VALUES ( '123 Yonge St', 'ON',
        'Canada', 'K1E 6T5', '647-123-4567');
INSERT INTO Address (street, province, country, zip, phone)
VALUES ('445 Avenue rd', 'ON',
        'Canada', 'M1C 6K5', '416-123-8569');
INSERT INTO Address ( street, province, country, zip, phone)
VALUES ('789 Keele St.', 'ON',
        'Canada', 'K3C 9T5', '416-123-9568');


Drop Table PO;

CREATE TABLE PO (
    id      INT   NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    uId     INT  NOT NULL references User(id),
   status varchar(20) CONSTRAINT status_ck CHECK (status IN ('ORDERED','PROCESSED','DENIED')) NOT NULL,    
    address INT NOT NULL constraint address check ( address >= 0 ),
    purchaseDate varchar(20)  not null, 
    PRIMARY KEY (id),
    FOREIGN KEY (address) REFERENCES Address (id) ON DELETE CASCADE
);

INSERT INTO PO ( uId, status, address, purchaseDate) VALUES ( 1, 'PROCESSED', '1', '1/1/2020');
INSERT INTO PO ( uId, status, address, purchaseDate) VALUES ( 2, 'DENIED', '2','2/1/2021');
INSERT INTO PO ( uId, status, address, purchaseDate) VALUES (2, 'ORDERED', '3', '3/4/2019');















DROP TABLE POItem;

CREATE TABLE POItem
(
    id       INT  NOT NULL  GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    bid      int  NOT NULL,
    price  double not null,
    quantity int not null default 1,
    PRIMARY KEY (id, bid),
    FOREIGN KEY (id) REFERENCES PO (id) ON DELETE CASCADE,
    FOREIGN KEY (bid) REFERENCES Book (bid) ON DELETE CASCADE,
    
    constraint Quantity check ( quantity >= 1 )
);


INSERT INTO POItem (bid, price) VALUES (1, 12.99);
INSERT INTO POItem ( bid, price) VALUES (2, 7.99);
INSERT INTO POItem (bid, price) VALUES (3, 5.99);



DROP TABLE VisitEvent;


CREATE TABLE VisitEvent(
    day varchar(20)  NOT NULL,
    bid  int  not null REFERENCES Book(bid),
     eventtype varchar(20) CONSTRAINT eventtype_ck CHECK (eventtype IN ('VIEW','CART','PURCHASE')) NOT NULL );




INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12202015', '1', 'VIEW');
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12242015', '1', 'CART');
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12252015', '1', 'PURCHASE');




DROP TABLE CARD;
DROP TABLE REVIEW;
CREATE TABLE Card (
    id int not null  GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    uId int references User(id) not null,
    cardNumber varchar(12) not null,
    cvc   varchar(3)  not null,
    expiryDate varchar(20)  not null,
    primary key (id, uId, cardNumber)
);

CREATE TABLE Review
(
    id   int  not null  GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    bId  int not null references Book(bId),
    uId  int  not null references User(Id),
    rating      double      not null,
    subject     varchar(100)  default 'Book Review',
    description varchar(1000) default 'Book is okay.',
    primary key (id, bId, uId, rating),
    constraint Rating CHECK ( rating >= 0 and rating <= 5)
);


