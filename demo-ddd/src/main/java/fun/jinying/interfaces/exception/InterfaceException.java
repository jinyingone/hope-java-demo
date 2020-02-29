package fun.jinying.interfaces.exception;

import lombok.Getter;

/**
 * @description: app异常
 * @author: sjy
 * @create: 2020-02-29 17:42
 **/
@Getter
public class InterfaceException extends RuntimeException {
    private InterfaceStatusEnum status;
    private String message;

    public InterfaceException(InterfaceStatusEnum status, String message) {
        this.status = status;
        this.message = message;
    }

    public InterfaceException(InterfaceStatusEnum status) {
        this.status = status;
    }

}
