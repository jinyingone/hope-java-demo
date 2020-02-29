package fun.jinying.interfaces.exception;

import lombok.AllArgsConstructor;
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
    /**
     * 注册失败
     */
    USER_REGISTER_FAIL(301, "注册失败,手机号已存在");

    private int value;
    private String message;

    InterfaceStatusEnum(int value, String message) {
        this.value = value;
        this.message = message;
    }
}
