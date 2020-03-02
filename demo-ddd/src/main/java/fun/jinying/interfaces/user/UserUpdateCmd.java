package fun.jinying.interfaces.user;

import lombok.Data;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-02 21:36
 **/
@Data
public class UserUpdateCmd {
    private String userId;
    private String userName;
    private String avatar;
    private String phone;
}
