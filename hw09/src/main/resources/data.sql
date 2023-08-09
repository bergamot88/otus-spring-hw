INSERT INTO author (first_name, last_name)
VALUES ('Eduard','Limonov'), ('Friedrich', 'Engels');

INSERT INTO genre (name)
VALUES ('Modern Prose'), ('Philosophy');

INSERT INTO book (label, author_id, genre_id)
VALUES ('American Vacation', '1', '1'),
       ('CMRT', '1', '1'),
       ('Anti-Duhring', '2', '2');