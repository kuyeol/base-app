CREATE TABLE users (
    id SERIAL not null primary key,
    userid varchar not null,
    description varchar not null,
    type varchar not null,
    value decimal not null
);