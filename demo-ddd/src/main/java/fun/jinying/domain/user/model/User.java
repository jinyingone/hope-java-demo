package fun.jinying.domain.user.model;

import fun.jinying.domain.shard.model.DomainEvent;
import fun.jinying.domain.shard.model.Entity;
import lombok.Data;

import java.util.Collections;
import java.util.Date;
import java.util.UUID;

/**
 * @description: 用户
 * @author: sjy
 * @create: 2020-02-26 22:01
 **/
@Data
public class User implements Entity {
    private Integer userId;
    private String userName;
    private String phone;
    private String avatar;
    private Date createTime;
    private Date updateTime;

    /**
     * 创建一个新用户
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

    public UserEvent register(){
        UserRegisteredEvent userRegisteredEvent = new UserRegisteredEvent();
        userRegisteredEvent.setUserId(this.userId.toString());
        userRegisteredEvent.setType(UserEventTypeEnum.REGISGERED);
        return userRegisteredEvent;
    }

    public UserEvent login() {
        UserLoggedEvent event = new UserLoggedEvent();
        event.setUserId(this.getUserId().toString());
        event.setType(UserEventTypeEnum.LOGED);
        return event;
    }

    public UserUpdatedEvent updateUserName(String userName){
        this.userName = userName;

        UserUpdatedEvent userUpdatedEvent = new UserUpdatedEvent();
        userUpdatedEvent.setType(UserEventTypeEnum.UPDATED);
        userUpdatedEvent.setUserId(this.getUserId().toString());
        userUpdatedEvent.setUpdatedFields(Collections.singletonMap("userName",userName));
        return userUpdatedEvent ;
    }

    /**
     * 更改用户信息
     *
     * @param user
     * @return
     */
    public User updateUser(User user) {
        return user;
    }

}
