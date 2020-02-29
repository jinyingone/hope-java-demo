package fun.jinying.interfaces.user;

import lombok.Data;

/**
 * @description: 用户注册命令
 * @author: sjy
 * @create: 2020-02-27 22:28
 **/
@Data
public class UserRegisterCmd {
    /**
     * 手机号
     */
    private String phone;
    /**
     * 短信验证码
     */
    private String smsCode;
}
