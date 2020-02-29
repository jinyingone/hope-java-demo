package fun.jinying.domain.user.repository;

import fun.jinying.domain.user.model.User;

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
}
