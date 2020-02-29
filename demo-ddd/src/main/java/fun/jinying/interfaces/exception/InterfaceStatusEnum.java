package fun.jinying.interfaces.exception;

import lombok.Getter;

/**
 * @description: 接口状态
 * @author: sjy
 * @create: 2020-02-29 21:52
 **/
@Getter
public enum InterfaceStatusEnum {
    /**
     * 成功
     */
    SUCCESS(200, "成功"),
    /**
     * 默认异常
     */
    SYSTEM_ERROR(500, "服务器内部错误,请稍后重试"),
    SYSTEM_ERROR_PARAMETER(501, "参数错误"),

    /**
     * 用户相关错误
     */
    USER_REGISTER_FAIL_EXISTS(301, "注册失败,手机号已存在"),
    USER_REGISTER_FAIL_CODE(302, "注册失败,验证码错误"),
    USER_LOGIN_FAIL_CODE(303, "登录失败,验证码错误"),
    USER_LOGIN_FAIL_NOT_EXISTS(304, "登录失败,用户不存在");


    private int value;
    private String message;

    InterfaceStatusEnum(int value, String message) {
        this.value = value;
        this.message = message;
    }
}
