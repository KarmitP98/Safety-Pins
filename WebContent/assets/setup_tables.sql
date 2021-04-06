/*
 Remove all the tables in same order
 */

DROP TABLE IF EXISTS Card;
DROP TABLE IF EXISTS Review;
DROP TABLE if exists POItem;
DROP TABLE if exists PO;
DROP TABLE if exists Address;
DROP TABLE if exists VisitEvent;
DROP TABLE if exists Book;
DROP TABLE IF EXISTS User;

use final;
/** bid: unique identifier of Book (like ISBN)
* title: title of Book
* price: unit price WHEN ordered
* author: name of authors
* category: as specified
  picture: Picture for the book
  Description: Short Description for the book
  Sold: Number of books sold
*/
CREATE TABLE Book
(
    bid         VARCHAR(20)                                                      NOT NULL,
    title       VARCHAR(60)                                                      NOT NULL,
    price       double                                                           NOT NULL,
    author      varchar(50)                                                      not null,
    category    ENUM ('Science','Fiction','Engineering','Maths','Modern','Kids') NOT NULL,
    picture     varchar(1000)                                                    not null,
    description varchar(10000)                                                   not null default 'Welcome to Book World!',
    sold        int                                                                       default 0,
    PRIMARY KEY (bid),
    constraint Sold check ( sold >= 0 )
);
#
# Adding data for table 'Book'
#
INSERT INTO Book
VALUES ('b001', 'No Place Like Here', 21.99, 'Christina June', 'Science', 'def', 'def', 0),
       ('b002', 'Educated', 12.99, 'Tara Westover', 'Maths', 'def', 'def', 0),
       ('b003', 'Killing Hemingway', 8.99, 'Brian D. Meeks', 'Fiction', 'def', 'def', 0);
#
/* Address
* id: address id
*
*/
CREATE TABLE Address
(
    id       INT UNSIGNED NOT NULL AUTO_INCREMENT,
    uId      int references User.id,
    street   VARCHAR(100) NOT NULL,
    province VARCHAR(20)  NOT NULL,
    country  VARCHAR(20)  NOT NULL,
    zip      VARCHAR(20)  NOT NULL,
    phone    VARCHAR(20),
    PRIMARY KEY (id)
);
#
# Inserting data for table 'address'
#
INSERT INTO Address (id, street, province, country, zip, phone)
VALUES (1, '123 Yonge St', 'ON',
        'Canada', 'K1E 6T5', '647-123-4567');
INSERT INTO Address (id, street, province, country, zip, phone)
VALUES (2, '445 Avenue rd', 'ON',
        'Canada', 'M1C 6K5', '416-123-8569');
INSERT INTO Address (id, street, province, country, zip, phone)
VALUES (3, '789 Keele St.', 'ON',
        'Canada', 'K3C 9T5', '416-123-9568');
#
#
/* Purchase Order
* lname: last name
* fname: first name
* id: purchase order id
* status:status of purchase
*/
CREATE TABLE PO
(
    id      INT UNSIGNED                          NOT NULL AUTO_INCREMENT,
    uId     VARCHAR(20)                           NOT NULL references User.id,
    status  ENUM ('ORDERED','PROCESSED','DENIED') NOT NULL,
    address INT UNSIGNED                          NOT NULL,
    PRIMARY KEY (id),
    INDEX (address),
    FOREIGN KEY (address) REFERENCES Address (id) ON DELETE CASCADE
);
#
# Inserting data for table 'PO'
#
INSERT INTO PO (id, uId, status, address)
VALUES (1, 1, 'PROCESSED', '1');
INSERT INTO PO (id, uId, status, address)
VALUES (2, 2, 'DENIED', '2');
INSERT INTO PO (id, uId, status, address)
VALUES (3, 2, 'ORDERED', '3');
#
#
/* Items on order
* id : purchase order id
* bid: unique identifier of Book
* price: unit price
*/
CREATE TABLE POItem
(
    id       INT UNSIGNED NOT NULL,
    bid      VARCHAR(20)  NOT NULL,
    price    INT UNSIGNED NOT NULL,
    quantity int unsigned not null default 1,
    PRIMARY KEY (id, bid),
    INDEX (id),
    FOREIGN KEY (id) REFERENCES PO (id) ON DELETE CASCADE,
    INDEX (bid),
    FOREIGN KEY (bid) REFERENCES Book (bid) ON DELETE CASCADE,
    constraint Quantity check ( quantity >= 1 )
);
#
# Inserting data for table 'POitem'
#
INSERT INTO POItem (id, bid, price)
VALUES (1, 'b001', '20');
INSERT INTO POItem (id, bid, price)
VALUES (2, 'b002', '201');
INSERT INTO POItem (id, bid, price)
VALUES (3, 'b003', '100');
#
#
/* visit to website
* day: date
* bid: unique identifier of Book
* eventtype: status of purchase
*/
CREATE TABLE VisitEvent
(
    day       varchar(8)                      NOT NULL,
    bid       varchar(20)                     not null REFERENCES Book.bid,
    eventtype ENUM ('VIEW','CART','PURCHASE') NOT NULL,
    FOREIGN KEY (bid) REFERENCES Book (bid)
);
#
# Dumping data for table 'VisitEvent'
#
INSERT INTO VisitEvent (day, bid, eventtype)
VALUES ('12202015', 'b001', 'VIEW');
INSERT INTO VisitEvent (day, bid, eventtype)
VALUES ('12242015', 'b001', 'CART');
INSERT INTO VisitEvent (day, bid, eventtype)
VALUES ('12252015', 'b001', 'PURCHASE');

/*
    User Table
    uId: number
    fName: First Name
    lName: Last Name
    email: User Email
    password: User Password
 */
CREATE TABLE User
(
    id       int         not null unique auto_increment,
    fName    varchar(10) not null,
    lName    varchar(20) not null,
    email    varchar(30) not null,
    password varchar(20) not null,
    primary key (id, fName, email)
);
/*
 Payment Card Table
 id: ID
 uId: User Id that owns this payment card
 cardNumber: Card Number
 cvc: 3 digit pin
 expiryDate: Expiry Date of the Card
 */
CREATE TABLE Card
(
    id         int         not null unique,
    uId        int references User.id,
    cardNumber varchar(12) not null,
    cvc        varchar(3)  not null,
    expiryDate date        not null,
    primary key (id, uId, cardNumber)
);

/*
 Review Table to store reviews and ratings for books
 id: ID
 bId: Book Id
 uID: Reviewer's ID
 rating: Rating out of 5
 subject: Short title of the review
 description: Review Content
 */
CREATE TABLE Review
(
    id          int         not null unique,
    bId         varchar(10) not null references Book.bId,
    uId         int         not null references User.uId,
    rating      double      not null,
    subject     varchar(100)  default 'Book Review',
    description varchar(1000) default 'Book is okay.',
    primary key (id, bId, uId, rating),
    constraint Rating CHECK ( rating >= 0 and rating <= 5)
);

/*
 Shopping Cart table
 NOTE: This table has the same fields as the PO Table.
 Depending on how the controller wants to implement it, we can have this table not necessary
 I suggest have this. So every time user checks out, the shopping cart fields get copied to the PO with status 'Purchased'
 and then get removed or set cells empty or null in this one. I guess have it removed and add another one with empty default fields I guess.
 Upto to the controller...
 */