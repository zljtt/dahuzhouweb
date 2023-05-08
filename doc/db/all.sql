drop table if exists `user`;
create table `user`
(
    id       bigint not null comment 'id',
    name     varchar(200) comment 'name',
    password varchar(200) comment 'password',
    primary key (id)
) default charset = utf8mb4 comment ='user';

insert into user (id, name, password)
values (0, 'zljtt', '12345');
