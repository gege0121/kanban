CREATE TABLE card (
    id              BIGSERIAL NOT NULL primary key,
    name            VARCHAR(30) not null unique,
    education       VARCHAR(30),
    email           VARCHAR(50),
    phone           VARCHAR(50),
    create_time     timestamp default CURRENT_DATE,
    status          INTEGER,
    rating          FLOAT,
    resume_name     VARCHAR(70),
    resume_key      VARCHAR(70)
);


CREATE TABLE comment (
    id              BIGSERIAL NOT NULL,
    user_id         BIGINT,
    card_id         BIGINT,
    content         VARCHAR(300),
    create_time     timestamp default CURRENT_DATE
);


CREATE TABLE rating (
    id              BIGSERIAL NOT NULL,
    user_id         BIGINT,
    card_id         BIGINT,
    score           FLOAT,
    create_time     timestamp default CURRENT_DATE
);


CREATE TABLE users (
    id              BIGSERIAL NOT NULL primary key,
    name            VARCHAR(30) not null unique,
    password        VARCHAR(64),
    secret_key      varchar(512),
    first_name      VARCHAR(30),
    last_name       VARCHAR(30),
    email           VARCHAR(50) not null unique
);

CREATE TABLE role (
    id                   BIGSERIAL NOT NULL unique primary key,
    name                 VARCHAR(30) not null unique,
    allowed_resource     VARCHAR(200),
    allowed_read         BOOLEAN not null default FALSE ,
    allowed_create       BOOLEAN not null default FALSE,
    allowed_update       BOOLEAN not null default FALSE,
    allowed_delete       BOOLEAN not null default FALSE
);

CREATE TABLE users_roles (
    user_id    BIGINT NOT NULL,
    role_id    BIGINT NOT NULL
);




