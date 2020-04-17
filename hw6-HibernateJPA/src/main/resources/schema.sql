CREATE TABLE AUTHORS
(
    ID   BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255)
);

CREATE TABLE COMMENTARIES
(
    ID   BIGINT AUTO_INCREMENT PRIMARY KEY,
    TEXT VARCHAR(255)
);

CREATE TABLE GENRES
(
    ID   BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255)
);

CREATE TABLE BOOKS
(
    ID   BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255)
);
CREATE TABLE BOOK_GENRE
(
    BOOK_ID   BIGINT,
    GENRE_ID  BIGINT,
    foreign key (BOOK_ID) references BOOKS (ID) ON DELETE CASCADE,
    foreign key (GENRE_ID) references GENRES (ID) ON DELETE CASCADE
);
CREATE TABLE BOOK_AUTHOR
(
    BOOK_ID   BIGINT,
    AUTHOR_ID BIGINT,
    foreign key (BOOK_ID) references BOOKS (ID) ON DELETE CASCADE,
    foreign key (AUTHOR_ID) references AUTHORS (ID) ON DELETE CASCADE
);

CREATE TABLE BOOK_COMMENTARY
(
    BOOK_ID   BIGINT,
    COMMENTARY_ID BIGINT,
    foreign key (BOOK_ID) references BOOKS (ID) ON DELETE CASCADE,
    foreign key (COMMENTARY_ID) references COMMENTARIES (ID) ON DELETE CASCADE
);