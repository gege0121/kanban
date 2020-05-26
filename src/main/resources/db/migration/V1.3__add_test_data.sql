
--insert into users (name, education, email, phone, cerate_time, status, score, resume_name, resume_key) values('Tom', 'GWU', '11@gmail.com', '2025916663', NULL,0, 4, 'Yihao's resume', '/Yihao's resume.pdf');

insert into users (name, password, email) values('Wang', '123', '111@gmail.com');
insert into users (name, password, email) values('Bob', '123', '222@gmail.com');
insert into users (name, password, email) values('Solomon', '123', '333@gmail.com');

insert into card (name, education, email, phone, create_time, status, rating, resume_name, resume_key) values('Candidate1', 'GWU', 'c1@gmail.com', '2025916663', NULL,0, 0, 'c1"s resume', '/c1"s resume.pdf');
insert into card (name, education, email, phone, create_time, status, rating, resume_name, resume_key) values('Candidate2', 'GWU', 'c1@gmail.com', '2025916663', NULL,2, 0, 'c2"s resume', '/c2"s resume.pdf');


insert into rating(user_id, card_id, score, create_time) values(1, 1, 3, NULL);
insert into rating(user_id, card_id, score, create_time) values(1, 2, 4, NULL);
insert into rating(user_id, card_id, score, create_time) values(2, 1, 2, NULL);
insert into rating(user_id, card_id, score, create_time) values(2, 2, 5, NULL);
insert into rating(user_id, card_id, score, create_time) values(3, 1, 4, NULL);
insert into rating(user_id, card_id, score, create_time) values(3, 2, 4, NULL);

insert into comment(user_id, card_id, content, create_time) values(1, 1, 'Strong hire', NULL);
insert into comment(user_id, card_id, content, create_time) values(2, 1, 'Nice', NULL);
insert into comment(user_id, card_id, content, create_time) values(1, 2, 'fair', NULL);
