package fun.jinying.interfaces.user.facade.internal;

import fun.jinying.application.SmsService;
import fun.jinying.application.UserAppService;
import fun.jinying.domain.user.model.User;
import fun.jinying.domain.user.model.UserUpdater;
import fun.jinying.interfaces.common.exception.InterfaceException;
import fun.jinying.interfaces.common.exception.InterfaceStatusEnum;
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
    private UserAppService userAppService;
    private SmsService smsService;

    public UserServiceFacadeImpl(UserAppService userAppService, SmsService smsService) {
        this.userAppService = userAppService;
        this.smsService = smsService;
    }

    @Override
    public UserDTO register(String phone, int smsCode) {
        if (!smsService.verifyCode(phone, smsCode)) {
            throw new InterfaceException(InterfaceStatusEnum.USER_REGISTER_FAIL_CODE);
        }
        userAppService.getRegisteredUser(phone).ifPresent(u -> {
            throw new InterfaceException(InterfaceStatusEnum.USER_REGISTER_FAIL_EXISTS);
        });
        User user = userAppService.register(phone);
        UserDTOAssembler dtoAssembler = new UserDTOAssembler();
        return dtoAssembler.toDTO(user);
    }

    @Override
    public UserDTO login(String phone, int smsCode) {
        if (!smsService.verifyCode(phone, smsCode)) {
            throw new InterfaceException(InterfaceStatusEnum.USER_LOGIN_FAIL_CODE);
        }
        User user = userAppService.getRegisteredUser(phone).orElseThrow(() -> new InterfaceException(InterfaceStatusEnum.USER_LOGIN_FAIL_NOT_EXISTS));
        userAppService.login(user);
        return new UserDTOAssembler().toDTO(user);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        User user = userAppService.getUser(userDTO.getUserId()).orElseThrow(() -> new InterfaceException(InterfaceStatusEnum.USER_FAIL_NOT_EXISTS));
        User updatedUser = userAppService.update(userDTO.getUserId(), new UserUpdater(userDTO.getUserName(), userDTO.getAvatar(), userDTO.getPhone()));
        return new UserDTOAssembler().toDTO(updatedUser);
    }

    @Override
    public UserDTO getUser(String userId) {
        User user = userAppService.getUser(userId).orElseThrow(() -> new InterfaceException(InterfaceStatusEnum.USER_FAIL_NOT_EXISTS));
        return new UserDTOAssembler().toDTO(user);
    }
}
