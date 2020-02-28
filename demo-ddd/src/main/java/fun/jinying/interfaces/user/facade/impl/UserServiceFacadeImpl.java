package fun.jinying.interfaces.user.facade.impl;

import fun.jinying.interfaces.user.facade.UserServiceFacade;
import fun.jinying.interfaces.user.facade.dto.UserDTO;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: sjy
 * @create: 2020-02-28 11:12
 **/
@Component
public class UserServiceFacadeImpl implements UserServiceFacade {
    @Override
    public UserDTO register(String userName, String avatar, String password) {
        return new UserDTO();
    }
}
