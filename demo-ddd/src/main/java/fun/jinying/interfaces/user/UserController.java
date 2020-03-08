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
        return userServiceFacade.register(cmd.getPhone(), cmd.getSmsCode());
    }

    @RequestMapping("/login")
    public UserDTO login(@Validated UserLoginCmd cmd) {
        return userServiceFacade.login(cmd.getPhone(), cmd.getSmsCode());
    }

    @RequestMapping("/update")
    public UserDTO update(@Validated UserUpdateCmd cmd) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(cmd.getUserId());
        userDTO.setAvatar(cmd.getAvatar());
        userDTO.setUserName(cmd.getUserName());
        userDTO.setPhone(cmd.getPhone());
        return userServiceFacade.update(userDTO);
    }
}
