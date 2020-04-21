package fun.jinying.domain.user.model;

import fun.jinying.domain.shard.model.Entity;
import lombok.*;

import java.util.Collections;
import java.util.Date;

/**
 * @description: 用户
 * @author: sjy
 * @create: 2020-02-26 22:01
 **/
@Getter
@Setter(AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode(of = {"userId"})
public class User implements Entity {
    private Integer userId;
    private String userName;
    private String phone;
    private String avatar;
    private Date createTime;
    private Date updateTime;

    /**
     * 创建一个新用户，这是一个工厂方法
     * @param userId userid
     * @param phone 手机号
     * @param userName 名称
     * @param avatar 头像
     * @return User
     */
    public static User createNewUser(Integer userId,String phone,String userName,String avatar) {
        User user = new User();
        user.setUserId(userId);
        user.setPhone(phone);
        user.setAvatar(avatar);
        user.setUserName(userName);
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        return user;
    }

    /**
     * 注册
     * @return 注册事件
     */
    public UserEvent register(){
        UserRegisteredEvent userRegisteredEvent = new UserRegisteredEvent();
        userRegisteredEvent.setUserId(this.userId.toString());
        userRegisteredEvent.setType(UserEventTypeEnum.REGISGERED);
        return userRegisteredEvent;
    }

    /**
     * 登陆
     * @return 登陆事件
     */
    public UserEvent login() {
        UserLoggedEvent event = new UserLoggedEvent();
        event.setUserId(this.getUserId().toString());
        event.setType(UserEventTypeEnum.LOGED);
        return event;
    }

    /**
     * 更新用户名
     * @param userName 新的用户名
     * @return 更新事件
     */
    public UserUpdatedEvent updateUserName(String userName){
        this.userName = userName;

        UserUpdatedEvent userUpdatedEvent = new UserUpdatedEvent();
        userUpdatedEvent.setType(UserEventTypeEnum.UPDATED);
        userUpdatedEvent.setUserId(this.getUserId().toString());
        userUpdatedEvent.setUpdatedFields(Collections.singletonMap("userName",userName));
        return userUpdatedEvent ;
    }

}
