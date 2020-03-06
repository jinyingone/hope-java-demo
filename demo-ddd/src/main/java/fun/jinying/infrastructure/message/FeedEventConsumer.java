package fun.jinying.infrastructure.message;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description: feed消费
 * @author: sjy
 * @create: 2020-03-06 23:47
 **/
@Component
public class FeedEventConsumer {
    @RabbitListener(bindings = {
            @QueueBinding(exchange = @Exchange(value = "demo-ddd.feed", type = ExchangeTypes.TOPIC),
                    key = "feed.#",
                    value = @Queue(name = "${spring.application.name}.feed", durable = "true"))
    })
    public void onMessage(Message message) {
        String str = new String(message.getBody());
        System.out.println(str);
    }
}
