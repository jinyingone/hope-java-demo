package fun.jinying.interfaces.user;

import fun.jinying.interfaces.user.facade.UserServiceFacade;
import fun.jinying.interfaces.user.facade.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 用户相关接口
 * @author: sjy
 * @create: 2020-02-26 22:32
 **/
@RestController
@RequestMapping("/v1/demo/ddd/user")
public class UserController {
    @Autowired
    private UserServiceFacade userServiceFacade;

    @RequestMapping("/register")
    public UserDTO register(@Validated UserRegisterCmd cmd) {
        UserDTO register = userServiceFacade.register(cmd.getPhone(), cmd.getSmsCode());
        return register;
    }
}
