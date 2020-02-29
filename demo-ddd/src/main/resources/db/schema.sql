DROP TABLE IF EXISTS user;
CREATE table user(
id int IDENTITY PRIMARY KEY,
user_id varchar(20) not null ,
phone varchar(11) not null ,
user_name varchar(64) not null ,
avatar varchar(256) not null,
password varchar(64) not null,
create_time date not null,
update_time date not null
);
create unique index uk_userId on user(user_id);
create unique index uk_phone on user(phone);
create unique index uk_userName on user(user_name);
