package fun.jinying.interfaces.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 异常处理
 * @author: sjy
 * @create: 2020-02-29 22:00
 **/
@ControllerAdvice
@RestController
public class ErrorController {
    @ExceptionHandler(InterfaceException.class)
    public Object interfaceExceptionHanler(InterfaceException e) {
        Map<String, Object> rtMap = new HashMap<>(2);
        rtMap.put("status", e.getStatus().getValue());
        rtMap.put("message", e.getStatus().getMessage());
        return rtMap;
    }

    @ExceptionHandler(Exception.class)
    public Object ExceptionHanler(Exception e) {
        Map<String, Object> rtMap = new HashMap<>(2);
        rtMap.put("status", InterfaceStatusEnum.SYSTEM_ERROR.getValue());
        rtMap.put("message", InterfaceStatusEnum.SYSTEM_ERROR.getMessage());
        return rtMap;
    }
}
