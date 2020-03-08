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
source_feed_id bigint not null,
user_id int not null,
text varchar(128),
action_type tinyint not null,
content_type tinyint not null,
status tinyint not null,
time datetime not null,
create_time datetime not null,
update_time datetime not null
);
create unique index uk_feedId on feed(feed_id);
comment on table feed is 'feed主表';


DROP TABLE IF EXISTS repost_feed;
CREATE table repost_feed(
id int IDENTITY PRIMARY KEY,
feed_id bigint not null,
reposted_feed_id bigint not null,
source_feed_id bigint not null,
feed_link varchar(512),
user_id int not null,
status tinyint not null,
time datetime not null,
create_time datetime not null,
update_time datetime not null
);
create unique index uk_rf_feedId on repost_feed(feed_id);
comment on table repost_feed is '转发类型feed';


DROP TABLE IF EXISTS relation;
CREATE table relation(
id int IDENTITY PRIMARY KEY,
user_id1 int not null,
user_id2 int not null,
fans_flag tinyint,
follow_flag tinyint,
follow_time datetime,
fans_time datetime,
create_time datetime not null,
update_time datetime not null
);
create unique index uk_r_ff on relation(user_id1,user_id2);
comment on table relation is '用户关系';

