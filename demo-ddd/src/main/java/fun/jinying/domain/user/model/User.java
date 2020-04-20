package fun.jinying.domain.user.model;

import fun.jinying.domain.shard.model.DomainEvent;
import fun.jinying.domain.shard.model.Entity;
import lombok.Data;

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
    private String password;
    private Date createTime;
    private Date updateTime;


    public UserEvent register(){
        UserRegisteredEvent userRegisteredEvent = new UserRegisteredEvent();
        userRegisteredEvent.setUserId(this.userId.toString());
        userRegisteredEvent.setType(UserEventTypeEnum.REGISGERED);
        return userRegisteredEvent;
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    public DomainEvent userLoggedEvent(User user) {
        UserLoggedEvent event = new UserLoggedEvent();
        event.setUserId(this.userId.toString());
        event.setType(UserEventTypeEnum.LOGED);
        return event;
    }

    public DomainEvent updateUserName(String userName){
        this.userName = userName;
        return null;
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

    /**
     * 设置默认名字
     *
     * @param uniqueNameFlag
     */
    public void setDefaultUserName(String uniqueNameFlag) {
        this.userName = "mm_" + uniqueNameFlag;
    }

    /**
     * 设置初始化密码
     */
    public void setInitializePassword() {
        this.password = UUID.randomUUID().toString();
    }
}
