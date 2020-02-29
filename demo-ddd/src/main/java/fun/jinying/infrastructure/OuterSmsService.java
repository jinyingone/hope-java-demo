package fun.jinying.infrastructure;

import fun.jinying.application.SmsService;
import org.springframework.stereotype.Component;

/**
 * @description: 模拟外部的短信服务
 * @author: sjy
 * @create: 2020-02-29 22:31
 **/
@Component
public class OuterSmsService implements SmsService {
    @Override
    public void sendVerificationCode(String phone) {

    }

    @Override
    public boolean verifyCode(String phone, int code) {
        return true;
    }
}
