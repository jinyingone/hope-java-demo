package fun.jinying.application;

import fun.jinying.domain.user.model.User;
import fun.jinying.domain.user.model.UserUpdater;

import java.util.Optional;

/**
 * @description: 用户服务
 * @author: sjy
 * @create: 2020-02-28 18:06
 **/
public interface UserService {

    /**
     * 注册
     *
     * @param phone 手机号
     * @return
     */
    User register(String phone);

    /**
     * 已注册用户信息
     *
     * @param phone
     * @return
     */
    Optional<User> getRegisteredUser(String phone);

    /**
     * 用户登录
     *
     * @param user
     */
    void login(User user);

    /**
     * 查询用户
     *
     * @param userId
     * @return
     */
    Optional<User> getUser(String userId);

    /**
     * 更新用户
     *
     * @param user
     * @param userUpdater
     * @return
     */
    User update(User user, UserUpdater userUpdater);
}
