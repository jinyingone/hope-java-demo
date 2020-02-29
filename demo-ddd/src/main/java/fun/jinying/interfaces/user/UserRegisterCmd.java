package fun.jinying.interfaces.user;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
    @NotNull
    private String phone;
    /**
     * 短信验证码
     */
    @NotNull
    @Min(100000)
    @Max(999999)
    private Integer smsCode;
}
