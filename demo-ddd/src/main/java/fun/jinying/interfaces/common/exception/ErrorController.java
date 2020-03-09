package fun.jinying.interfaces.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: 异常处理
 * @author: sjy
 * @create: 2020-02-29 22:00
 **/
@ControllerAdvice
@RestController
@Slf4j
public class ErrorController {
    @ExceptionHandler(InterfaceException.class)
    public Object interfaceExceptionHanler(InterfaceException e) {
        Map<String, Object> rtMap = new HashMap<>(2);
        rtMap.put("status", e.getStatus().getValue());
        rtMap.put("message", e.getStatus().getMessage());
        return rtMap;
    }

    @ExceptionHandler(BindException.class)
    public Object bindException(HttpServletRequest request, BindException e) {
        Map<String, Object> rtMap = new HashMap<>(2);
        log.error("bindException,path={},params={},exception={}", request.getRequestURI(), request.getParameterMap(), e);
        rtMap.put("status", InterfaceStatusEnum.SYSTEM_ERROR_PARAMETER.getValue());
        List<String> collect = e.getBindingResult().getAllErrors().stream().map(ObjectError::toString).collect(Collectors.toList());
        rtMap.put("message", collect);
        return rtMap;
    }

    @ExceptionHandler(Exception.class)
    public Object exception(HttpServletRequest request, Exception e) {
        log.error("exception,path={},params={},exception={}", request.getRequestURI(), request.getParameterMap(), e);
        Map<String, Object> rtMap = new HashMap<>(2);
        rtMap.put("status", InterfaceStatusEnum.SYSTEM_ERROR.getValue());
        rtMap.put("message", InterfaceStatusEnum.SYSTEM_ERROR.getMessage());
        return rtMap;
    }
}
