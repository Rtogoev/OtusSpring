DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS AUTHORS;
DROP TABLE IF EXISTS GENRES;
CREATE TABLE AUTHORS
(
    ID   BIGINT PRIMARY KEY,
    NAME VARCHAR(255)
);
CREATE TABLE BOOKS
(
    ID   BIGINT PRIMARY KEY,
    NAME VARCHAR(255)
);
CREATE TABLE GENRES
(
    ID   BIGINT PRIMARY KEY,
    NAME VARCHAR(255)
);
CREATE TABLE BOOK_GENRE
(
    BOOK_ID   BIGINT,
    GENRE_ID  BIGINT,
    foreign key (BOOK_ID) references BOOKS (ID),
    foreign key (GENRE_ID) references GENRES (ID)
);
CREATE TABLE BOOK_AUTHOR
(
    BOOK_ID   BIGINT,
    AUTHOR_ID BIGINT,
    foreign key (BOOK_ID) references BOOKS (ID),
    foreign key (AUTHOR_ID) references AUTHORS (ID)
);