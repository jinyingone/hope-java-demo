package fun.jinying.infrastructure.message;

import fun.jinying.domain.service.EventProducer;
import fun.jinying.domain.user.model.UserEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @description: 用户服务发送者
 * @author: sjy
 * @create: 2020-03-01 17:43
 **/
@Component
public class UserEventProducer implements EventProducer<UserEvent> {
    private RabbitTemplate rabbitTemplate;
    public static final String ROUTING_KEY = "user";
    public static final String EXCHANGE = System.getProperty("spring.application.name") + "user";

    public UserEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendEvent(UserEvent event) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY + event.getType(), event);
    }
}
