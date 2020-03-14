package fun.jinying.infrastructure.message;

import fun.jinying.application.FeedAppService;
import fun.jinying.application.TimelineAppService;
import fun.jinying.domain.feed.model.Feed;
import fun.jinying.domain.feed.model.FeedEvent;
import fun.jinying.infrastructure.utils.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @description: feed消费
 * @author: sjy
 * @create: 2020-03-06 23:47
 **/
@Component
@Slf4j
public class FeedEventConsumer {
    @Autowired
    private FeedAppService feedAppService;
    @Autowired
    private TimelineAppService timelineAppService;

    @RabbitListener(bindings = {
            @QueueBinding(exchange = @Exchange(value = "demo-ddd.feed", type = ExchangeTypes.TOPIC),
                    key = {"feed.PUBLISH"},
                    value = @Queue(name = "${spring.application.name}.feedPublish", durable = "true"))
    })
    public void onPublishMessage(Message message) {
        String body = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("consuming,body={},properties={}", body, message.getMessageProperties());
        FeedEvent feedEvent = JSON.fromJson(body, FeedEvent.class);

        Feed feed = feedAppService.publish(feedEvent);
    }

    @RabbitListener(bindings = {
            @QueueBinding(exchange = @Exchange(value = "demo-ddd.feed", type = ExchangeTypes.TOPIC),
                    key = {"feed.CREATED"},
                    value = @Queue(name = "${spring.application.name}.feedCreated", durable = "true"))
    })
    public void onCreatedMessage(Message message) {
        String body = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("consuming,body={},properties={}", body, message.getMessageProperties());
        FeedEvent feedEvent = JSON.fromJson(body, FeedEvent.class);
        timelineAppService.saveTimeLine(feedEvent);
    }
}
