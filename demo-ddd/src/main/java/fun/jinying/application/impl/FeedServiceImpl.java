package fun.jinying.application.impl;

import fun.jinying.application.FeedService;
import fun.jinying.domain.feed.factory.FeedFactory;
import fun.jinying.domain.feed.model.Feed;
import fun.jinying.domain.feed.model.FeedActionTypeEnum;
import fun.jinying.domain.feed.model.FeedEvent;
import fun.jinying.domain.feed.model.RepostFeed;
import fun.jinying.domain.feed.repository.FeedRepository;
import fun.jinying.domain.shard.model.EventProducer;
import fun.jinying.interfaces.common.exception.InterfaceException;
import fun.jinying.interfaces.common.exception.InterfaceStatusEnum;
import fun.jinying.interfaces.feed.PublishCmd;
import fun.jinying.interfaces.feed.RepostCmd;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-06 22:31
 **/
@Component
public class FeedServiceImpl implements FeedService {
    private FeedFactory feedFactory;
    private EventProducer<FeedEvent> feedEventEventProducer;
    private FeedRepository feedRepository;

    public FeedServiceImpl(FeedFactory feedFactory, EventProducer<FeedEvent> feedEventEventProducer, FeedRepository feedRepository) {
        this.feedFactory = feedFactory;
        this.feedEventEventProducer = feedEventEventProducer;
        this.feedRepository = feedRepository;
    }

    @Override
    public Feed publish(PublishCmd cmd) {
        Feed feed = feedFactory.newFeed(cmd.getLogUserId(), cmd.getText());
        feedEventEventProducer.sendEvent(feedFactory.newPublishEvent(feed));
        return feed;
    }

    @Override
    public Feed publish(FeedEvent feedEvent) {
        Feed feed = feedEvent.getFeed();
        feedRepository.saveFeed(feed);
        feedEventEventProducer.sendEvent(feedFactory.newCreatedEvent(feed));
        return feed;
    }

    @Override
    @Transactional
    public Feed repost(RepostCmd repostCmd) {
        Feed repostedFeed = feedRepository.getFeed(Long.valueOf(repostCmd.getFeedId())).orElseThrow(() -> new InterfaceException(InterfaceStatusEnum.FEED_NOT_EXITS));
        Feed feed = feedFactory.newFeed(repostCmd.getLogUserId(), repostCmd.getText(), FeedActionTypeEnum.REPOST);
        RepostFeed repostFeed = feed.repost(repostedFeed);
        feedRepository.saveFeed(feed);
        feedRepository.saveRepostFeed(repostFeed);
        feedEventEventProducer.sendEvent(feedFactory.newCreatedEvent(feed));
        return feed;
    }
}
