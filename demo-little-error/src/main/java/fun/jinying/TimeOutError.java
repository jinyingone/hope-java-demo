package fun.jinying;

import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.concurrent.TimeoutException;

/**
 * 超时问题
 *
 * @author jy
 * @date 2019-10-24 下午5:43
 **/
public class TimeOutError {
    /**
     * 请求网络资源,经常会超时
     * 常见处理策略:
     * 1.增加超时时间
     * 2.如果是幂等的请求,可以添加重试
     */
    public void requestNetResource() throws TimeoutException {
        RetryTemplate retryTemplate = new RetryTemplate();
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(3);
        retryTemplate.setRetryPolicy(retryPolicy);

        Object resp = retryTemplate.execute(context -> {
            System.out.println(context.getRetryCount());
            throw new TimeoutException("模拟超时异常");
        });

        System.out.println(resp);
    }
}
