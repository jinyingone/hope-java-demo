package fun.jinying.interfaces.user.facade;

import fun.jinying.interfaces.user.facade.dto.UserDTO;

/**
 * @description: 用户facade
 * @author: sjy
 * @create: 2020-02-27 22:30
 **/
public interface UserServiceFacade {
    /**
     * 手机
     *
     * @param phone
     * @param smsCode
     * @return
     */
    UserDTO register(String phone, int smsCode);

    /**
     * 登录
     *
     * @param phone
     * @param smsCode
     * @return
     */
    UserDTO login(String phone, int smsCode);
}
