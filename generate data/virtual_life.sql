DROP DATABASE virtual_life;

CREATE DATABASE virtual_life;

\c virtual_life

CREATE TABLE IF NOT EXISTS virtual_life_user(
    id SERIAL,
    first_name VARCHAR(128) NOT NULL,
    last_name VARCHAR(128) NOT NULL,
    email VARCHAR(128) NOT NULL,
    user_password VARCHAR(128) NOT NULL,
    dob DATE,
    date_joined DATE NOT NULL DEFAULT CURRENT_DATE,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS friendship(
    user_id INTEGER NOT NULL,
    friend_id INTEGER NOT NULL,
    date_friendship DATE NOT NULL DEFAULT CURRENT_DATE,
    PRIMARY KEY(user_id, friend_id),
    FOREIGN KEY(user_id) REFERENCES virtual_life_user(id) ON DELETE CASCADE,
    FOREIGN KEY(friend_id) REFERENCES virtual_life_user(id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX friendship_ignore_duplicates
ON friendship(least(user_id, friend_id), greatest(user_id, friend_id));

CREATE TABLE IF NOT EXISTS hobby(
    id serial,
    hobby_name VARCHAR(128) UNIQUE NOT NULL,
    PRIMARY KEY(id)
);

ALTER TABLE hobby
ADD CONSTRAINT hobby_name_is_lowercase
CHECK(hobby_name = LOWER(hobby_name));


CREATE TABLE IF NOT EXISTS user_hobby(
    user_id INTEGER NOT NULL,
    hobby_id INTEGER NOT NULL,
    FOREIGN KEY(user_id) REFERENCES virtual_life_user(id),
    FOREIGN KEY(hobby_id) REFERENCES hobby(id),
    UNIQUE(user_id, hobby_id)
);

CREATE TABLE IF NOT EXISTS post(
    id serial,
    author INTEGER NOT NULL,
    post_text TEXT NOT NULL,
    date_posted DATE NOT NULL DEFAULT CURRENT_DATE,
    PRIMARY KEY(id),
    FOREIGN KEY(author) REFERENCES virtual_life_user(id)
);

-- CREATE TABLE IF NOT EXISTS user_comment(
--     id serial,
--     user_id INTEGER NOT NULL,
--     post_id INTEGER NOT NULL,
--     comment_text TEXT NOT NULL,
--     PRIMARY KEY(id),
--     FOREIGN KEY(user_id) REFERENCES virtual_life_user(id),
--     FOREIGN KEY(post_id) REFERENCES post(id)
-- );

CREATE TABLE IF NOT EXISTS reaction(
    id serial,
    reaction_name VARCHAR(128) UNIQUE NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS user_post_reaction(
    post_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    reaction_id INTEGER NOT NULL,
    PRIMARY KEY(post_id, user_id, reaction_id),
    FOREIGN KEY(post_id) REFERENCES post(id),
    FOREIGN KEY(user_id) REFERENCES virtual_life_user(id),
    FOREIGN KEY(reaction_id) REFERENCES reaction(id)
);

INSERT INTO virtual_life_user (first_name,
								last_name,
								email,
								user_password,
								dob)
VALUES ('Muhammad', 'Ihab', 'm.ihab@mail.com', 'password', '2022-01-01'),
		('Ihab', 'Anass', 'i.anass@mail.com', 'password', '2022-01-01'),
		('Hazem', 'Mostafa', 'h.mostafa@mail.com', 'password', '2022-01-01'),
		('Mrwan', 'Shahin', 'm.shahin@mail.com', 'password', '2022-01-01'),
        ('Omar', 'Elsaid', 'o.elsaid@mail.com', 'password', '2022-01-01'),
		('Ahmed', 'Elsaid', 'a.elsaid@mail.com', 'password', '2022-01-01'),
		('Muhammad', 'Adel', 'm.adel@mail.com', 'password', '2022-01-01'),
		('Adham', 'Muhammad', 'a.muhammad@mail.com', 'password', '2022-01-01');


INSERT INTO friendship (user_id, friend_id)
VALUES (1, 2),
		(1, 3),
		(3, 2),
		(4, 2),
		(4, 1),
        (7, 1),
        (7, 2),
        (7, 4),
        (7, 8),
        (8, 1),
        (9, 1),
        (10, 2),
        (10, 4);

INSERT INTO hobby (hobby_name)
VALUES  ('chess'),
        ('football'),
        ('tennis'),
        ('drawing'),
        ('competitive-programming'),
        ('reading'),
        ('horse-riding'),
        ('running'),
        ('bicycling'),
        ('math');

INSERT INTO user_hobby (user_id, hobby_id)
VALUES (1, 1),
        (1, 4),
        (2, 1),
        (2, 2),
        (3, 3),
        (4, 2),
        (7, 1),
        (7, 10),
        (7, 2),
        (8, 6),
        (8, 8),
        (9, 1),
        (9, 2),
        (9, 6),
        (10, 1),
        (10, 2),
        (10, 5),
        (10, 6),
        (10, 10),
        (1, 10);

INSERT INTO post (author,
                post_text)
VALUES (1, 'text text text text'),
        (1, 'views views views'),
        (3, 'text text text text'),
        (3, 'views views'),
        (4, 'text text text text'),
        (7, 'views views views views views views'),
        (10, 'text text text text');


INSERT INTO reaction (reaction_name)
VALUES ('like'),
        ('love'),
        ('laugh'),
        ('sad'),
        ('care'),
        ('angry');

INSERT INTO user_post_reaction (post_id, user_id, reaction_id)
VALUES (1, 2, 1),
        (1, 3, 2),
        (1, 4, 1),
        (1, 7, 2),
        (2, 3, 4),
        (2, 4, 1),
        (2, 8, 2),
        (2, 3, 3),
        (3, 7, 4),
        (3, 2, 3),
        (3, 4, 1),
        (4, 7, 2),
        (4, 2, 3),
        (5, 4, 1),
        (5, 7, 2),
        (6, 2, 3),
        (7, 3, 1),
        (7, 7, 2),
        (7, 2, 2),
        (7, 4, 3);