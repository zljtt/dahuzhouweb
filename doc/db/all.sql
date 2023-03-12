drop table if exists `card`;
create table `card`
(
    set_code         varchar(5) not null comment 'set code',
    collector_number varchar(5) not null comment 'collector number',
    foil             boolean comment 'foil',
    primary key (set_code, collector_number)
) default charset = utf8mb4 comment ='card';

drop table if exists `deck_cards`;
create table `deck_cards`
(
    deck_id               bigint not null comment 'deck id',
    card_set_code         varchar(5) comment 'card set code',
    card_collector_number varchar(5) comment 'card collector number',
    foil                  boolean default false comment 'foil',
    amount                int     default 1 comment 'amount',
    primary key (deck_id, card_set_code, card_collector_number)
) default charset = utf8mb4 comment ='deck_cards';

drop table if exists `deck`;
create table `deck`
(
    id             bigint not null comment 'id',
    name           varchar(200) comment 'name',
    description    varchar(200) comment 'description',
    format         varchar(50) comment 'format',
    owner          bigint comment 'deck owner',
    is_public      bool comment 'is public',
    view_count     int default 0 comment 'deck viewed count',
    favorite_count int default 0 comment 'deck loved count',
    primary key (id)
) default charset = utf8mb4 comment ='deck';

drop table if exists `user`;
create table `user`
(
    id       bigint not null comment 'id',
    name     varchar(200) comment 'name',
    password varchar(200) comment 'password',
    primary key (id)
) default charset = utf8mb4 comment ='user';

insert into user (id, name, password)
values (0, 'zljtt', '111222333');

insert into deck (id, name, description, format, owner, is_public)
values (0, 'Atraxa Saga', null, 'EDH', 0, true);

insert into deck (id, name, description, format, owner, is_public)
values (1, 'Lonis Clue', 'clue artifact deck', 'EDH', 0, true);

insert into deck_cards (deck_id, card_set_code, card_collector_number)
values (0, '2xm', '353');

insert into deck_cards (deck_id, card_set_code, card_collector_number)
values (0, '40k', '238');

insert into deck_cards (deck_id, card_set_code, card_collector_number)
values (1, 'mh2', '370');

insert into deck_cards (deck_id, card_set_code, card_collector_number)
values (1, '2xm', '270');
