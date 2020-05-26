ALTER TABLE users_roles
    ADD CONSTRAINT users_fk FOREIGN KEY ( user_id )
        REFERENCES users ( id );

ALTER TABLE users_roles
    ADD CONSTRAINT role_fk FOREIGN KEY ( role_id )
        REFERENCES role ( id );

alter table comment
ADD CONSTRAINT FK_comment_users
foreign key (user_id) references users(id) on delete cascade;

alter table comment
ADD CONSTRAINT FK_comment_card
foreign key (card_id) references card(id)  on delete cascade;

alter table rating
ADD CONSTRAINT FK_rating_users
foreign key (user_id) references users(id) on delete cascade;

alter table rating
ADD CONSTRAINT FK_rating_card
foreign key (card_id) references card(id)  on delete cascade;




