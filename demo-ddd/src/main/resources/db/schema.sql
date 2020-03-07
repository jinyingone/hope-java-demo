DROP TABLE IF EXISTS user;
CREATE table user(
id int IDENTITY PRIMARY KEY,
user_id int not null ,
phone varchar(11) not null ,
user_name varchar(64) not null ,
avatar varchar(256) not null,
password varchar(64) not null,
create_time datetime not null,
update_time datetime not null
);
create unique index uk_userId on user(user_id);
create unique index uk_phone on user(phone);
create unique index uk_userName on user(user_name);

DROP TABLE IF EXISTS feed;
CREATE table feed(
id int IDENTITY PRIMARY KEY,
feed_id bigint not null,
user_id int not null,
text varchar(128),
type tinyint not null,
status tinyint not null,
time datetime not null,
create_time datetime not null,
update_time datetime not null
);
create unique index uk_feedId on feed(feed_id);

