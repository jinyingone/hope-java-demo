package fun.jinying.domain.user.repository;

import fun.jinying.domain.user.model.User;
import fun.jinying.domain.user.model.UserUpdater;

import java.util.Optional;

/**
 * @description: 用户仓储
 * @author: sjy
 * @create: 2020-02-26 22:30
 **/
public interface UserRepository {
    /**
     * 下一个userId
     *
     * @return
     */
    Integer nextUserId();

    /**
     * 默认昵称序列
     *
     * @return
     */
    Integer nextUserNameSequence();

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    int saveUser(User user);

    /**
     * 根据手机号查找
     *
     * @param phone
     * @return
     */
    Optional<User> getByPhone(String phone);

    /**
     * 根据id查找
     *
     * @param userId
     * @return
     */
    Optional<User> getByUserId(String userId);

    /**
     * 更新用户
     *
     * @param userId
     * @param userUpdater
     * @return
     */
    int update(Integer userId, UserUpdater userUpdater);
}
