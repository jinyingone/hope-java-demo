package fun.jinying.domain.user.model;

import fun.jinying.domain.shard.model.Entity;
import lombok.Data;

import java.util.Date;

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


    /**
     * 登录
     *
     * @param user
     * @return
     */
    public LoginStatusEnum login(User user) {
        return LoginStatusEnum.SUCCESS;
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

    public void setDefaultUserName(String uniqueNameFlag) {
        this.userName = "mm_" + uniqueNameFlag;
    }


}
