package fun.jinying.application;

/**
 * @description: 短信服务
 * @author: sjy
 * @create: 2020-02-29 22:24
 **/
public interface SmsService {
    /**
     * 发送短信验证码
     *
     * @param phone
     */
    void sendVerificationCode(String phone);

    /**
     * 验证校验码
     *
     * @param phone
     * @param code
     * @return
     */
    boolean verifyCode(String phone, int code);
}
