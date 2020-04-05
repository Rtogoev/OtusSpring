DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS AUTHORS;
DROP TABLE IF EXISTS GENRES;
CREATE TABLE AUTHORS(ID BIGINT PRIMARY KEY, NAME VARCHAR(255));
CREATE TABLE GENRES(ID BIGINT PRIMARY KEY, NAME VARCHAR(255));
CREATE TABLE BOOKS(
ID BIGINT PRIMARY KEY,
NAME VARCHAR(255),
AUTHORS_ID ARRAY,
GENRES_ID ARRAY
);