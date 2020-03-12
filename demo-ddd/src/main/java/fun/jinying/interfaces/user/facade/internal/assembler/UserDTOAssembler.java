package fun.jinying.interfaces.user.facade.internal.assembler;

import fun.jinying.domain.user.model.User;
import fun.jinying.interfaces.user.facade.dto.UserDTO;

/**
 * @description:
 * @author: sjy
 * @create: 2020-02-28 21:37
 **/
public class UserDTOAssembler {

    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId().toString());
        userDTO.setUserName(user.getUserName());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setPhone(user.getPhone());
        return userDTO;
    }

}
