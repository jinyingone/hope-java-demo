package fun.jinying.infrastructure.message;

import fun.jinying.domain.service.EventProducer;
import fun.jinying.domain.user.model.UserEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Component;

/**
 * @description: 用户服务发送者
 * @author: sjy
 * @create: 2020-03-01 17:43
 **/
@Component
public class UserEventProducer implements EventProducer<UserEvent> {
    private RabbitTemplate rabbitTemplate;
    public static final String USER_ROUTING_KEY = "user.";
    public static final String USER_EXCHANGE = "user";

    public UserEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendEvent(UserEvent event) {
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.convertAndSend(USER_EXCHANGE, USER_ROUTING_KEY + event.getType(), event);
    }
}
