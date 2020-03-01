package fun.jinying.interfaces.user.facade.internal;

import fun.jinying.application.SmsService;
import fun.jinying.application.UserService;
import fun.jinying.domain.user.model.User;
import fun.jinying.interfaces.exception.InterfaceException;
import fun.jinying.interfaces.exception.InterfaceStatusEnum;
import fun.jinying.interfaces.user.facade.UserServiceFacade;
import fun.jinying.interfaces.user.facade.dto.UserDTO;
import fun.jinying.interfaces.user.facade.internal.assembler.UserDTOAssembler;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: sjy
 * @create: 2020-02-28 11:12
 **/
@Component
public class UserServiceFacadeImpl implements UserServiceFacade {
    private UserService userService;
    private SmsService smsService;

    public UserServiceFacadeImpl(UserService userService, SmsService smsService) {
        this.userService = userService;
        this.smsService = smsService;
    }

    @Override
    public UserDTO register(String phone, int smsCode) {
        if (!smsService.verifyCode(phone, smsCode)) {
            throw new InterfaceException(InterfaceStatusEnum.USER_REGISTER_FAIL_CODE);
        }
        userService.getRegisteredUser(phone).ifPresent(u -> {
            throw new InterfaceException(InterfaceStatusEnum.USER_REGISTER_FAIL_EXISTS);
        });
        User user = userService.register(phone);
        UserDTOAssembler dtoAssembler = new UserDTOAssembler();
        return dtoAssembler.toDTO(user);
    }

    @Override
    public UserDTO login(String phone, int smsCode) {
        if (!smsService.verifyCode(phone, smsCode)) {
            throw new InterfaceException(InterfaceStatusEnum.USER_LOGIN_FAIL_CODE);
        }
        User user = userService.getRegisteredUser(phone).orElseThrow(() -> new InterfaceException(InterfaceStatusEnum.USER_LOGIN_FAIL_NOT_EXISTS));
        userService.login(user);
        return new UserDTOAssembler().toDTO(user);
    }
}
