CREATE TABLE author(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50));

CREATE TABLE genre(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50));

CREATE TABLE book(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    label VARCHAR(100),
    author_id BIGINT,
    genre_id BIGINT,
    FOREIGN KEY (author_id) REFERENCES author(id),
    FOREIGN KEY (genre_id) REFERENCES genre(id));