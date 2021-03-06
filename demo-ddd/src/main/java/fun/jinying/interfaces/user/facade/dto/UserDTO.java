package fun.jinying.interfaces.user.facade.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 用户数据
 * @author: sjy
 * @create: 2020-02-27 22:24
 **/
@Data
public class UserDTO implements Serializable {
    private String userId;
    private String userName;
    private String avatar;
    private String phone;
}
