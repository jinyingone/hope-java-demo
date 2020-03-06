package fun.jinying.application.impl;

import fun.jinying.application.FeedService;
import fun.jinying.domain.feed.factory.FeedFactory;
import fun.jinying.domain.feed.model.Feed;
import fun.jinying.domain.feed.model.FeedEvent;
import fun.jinying.domain.service.EventProducer;
import fun.jinying.interfaces.feed.PublishCmd;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-06 22:31
 **/
@Component
public class FeedServiceImpl implements FeedService {
    private FeedFactory feedFactory;
    private EventProducer<FeedEvent> feedEventEventProducer;

    public FeedServiceImpl(FeedFactory feedFactory, EventProducer<FeedEvent> feedEventEventProducer) {
        this.feedFactory = feedFactory;
        this.feedEventEventProducer = feedEventEventProducer;
    }

    @Override
    public Feed publish(PublishCmd cmd) {
        Feed feed = feedFactory.newFeed(cmd.getLogUserId(), cmd.getText());
        feedEventEventProducer.sendEvent(feedFactory.newPublishEvent(feed));
        return feed;
    }
}
