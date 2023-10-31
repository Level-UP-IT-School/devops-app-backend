create table person
(
    id           int primary key generated by default as identity,
    person_name  varchar(50),
    age          int,
    phone_number varchar(20)
);

create table book
(
    id           int generated by default as identity,
    book_name  varchar(255),
    author  varchar(255),
    genre  varchar(255),
    description  varchar(10000),
    person_id          int references person(id),
    PRIMARY KEY (id),
    FOREIGN KEY (person_id)
        REFERENCES person(id)
        ON DELETE CASCADE
);
