DROP TABLE IF EXISTS user;
CREATE table user(
id int IDENTITY PRIMARY KEY,
user_id varchar(20) not null ,
phone varchar(11) ,
user_name varchar(64),
avatar varchar(128),
password varchar(32),
create_time date,
update_time date
);
