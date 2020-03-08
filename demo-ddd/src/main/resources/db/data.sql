insert into user (user_id,phone,user_name,avatar,password,create_time,update_time)values(1,18999999991,'admin','https://profile.csdnimg.cn/6/7/C/1_leslies2','admin',now(),now());
insert into user (user_id,phone,user_name,avatar,password,create_time,update_time)values(2,18999999992,'guest','https://profile.csdnimg.cn/6/7/C/1_leslies2','guest',now(),now());

insert into feed(feed_id,source_feed_id,user_id,text,action_type,content_type,status,time,create_time,update_time) values(1,1,1,'你好,demo-sns',1,1,1,now(),now(),now())