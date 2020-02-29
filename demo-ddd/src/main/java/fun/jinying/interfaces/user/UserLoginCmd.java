package fun.jinying.interfaces.user;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: sjy
 * @create: 2020-02-29 23:12
 **/
@Data
public class UserLoginCmd {
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
