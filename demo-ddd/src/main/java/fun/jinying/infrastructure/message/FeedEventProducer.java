package fun.jinying.infrastructure.message;

import fun.jinying.domain.feed.model.FeedEvent;
import fun.jinying.domain.shard.model.EventProducer;
import lombok.Getter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @description: feed事件生产者
 * @author: sjy
 * @create: 2020-03-06 23:13
 **/
@Getter
@Component
public class FeedEventProducer implements EventProducer<FeedEvent> {
    private RabbitTemplate rabbitTemplate;
    private String exchange;
    private String routingKey;

    public FeedEventProducer(RabbitTemplate rabbitTemplate, @Value("${spring.application.name}") String applicationName) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = applicationName + ".feed";
        this.routingKey = "feed.";
    }

    @Override
    public void sendEvent(FeedEvent event) {
        rabbitTemplate.convertAndSend(exchange, routingKey + event.getType(), event);
    }

}
