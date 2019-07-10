package fun.jinying.demo;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * mq消费者
 *
 * @author jy
 * @date 2019-07-10 下午3:37
 **/
@Component
public class MQListener {
    @JmsListener(destination = "xxxTopic", containerFactory = "topicContainerFactory")
    public void onMessage(String message) {
        System.out.println(message);
    }

    @JmsListener(destination = "xxxQueue", containerFactory = "queueContainerFactory")
    public void onMessageQueue(String message) {
        System.out.println(message);
    }


}
