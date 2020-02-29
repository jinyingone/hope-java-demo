package fun.jinying.application;

import fun.jinying.domain.user.model.User;

/**
 * @description: 用户服务
 * @author: sjy
 * @create: 2020-02-28 18:06
 **/
public interface UserService {

    /**
     * 注册
     *
     * @param phone
     * @return
     */
    User register(String phone);
}
