package fun.jinying.interfaces.user.facade.internal;

import fun.jinying.application.UserService;
import fun.jinying.domain.user.model.User;
import fun.jinying.interfaces.user.facade.UserServiceFacade;
import fun.jinying.interfaces.user.facade.dto.UserDTO;
import fun.jinying.interfaces.user.facade.internal.assembler.UserDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: sjy
 * @create: 2020-02-28 11:12
 **/
@Component
public class UserServiceFacadeImpl implements UserServiceFacade {
    @Autowired
    private UserService userService;

    @Override
    public UserDTO register(String phone, String smsCode) {
        User user = userService.register(phone);
        UserDTOAssembler dtoAssembler = new UserDTOAssembler();
        return dtoAssembler.toDTO(user);
    }
}
