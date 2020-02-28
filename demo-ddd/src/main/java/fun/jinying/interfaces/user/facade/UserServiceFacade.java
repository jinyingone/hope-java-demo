package fun.jinying.interfaces.user.facade;

import fun.jinying.interfaces.user.facade.dto.UserDTO;
import org.springframework.stereotype.Component;

/**
 * @description: 用户facade
 * @author: sjy
 * @create: 2020-02-27 22:30
 **/
public interface UserServiceFacade {
    /**
     * 注册
     *
     * @param userName
     * @param avatar
     * @param password
     * @return
     */
    UserDTO register(String userName, String avatar, String password);
}
