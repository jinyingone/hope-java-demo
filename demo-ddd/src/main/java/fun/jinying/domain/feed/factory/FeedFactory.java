package fun.jinying.domain.feed.factory;

import fun.jinying.domain.feed.model.*;
import fun.jinying.domain.feed.repository.FeedRepository;

import java.util.Date;

/**
 * @description: feed工厂
 * @author: sjy
 * @create: 2020-03-06 22:32
 **/
public class FeedFactory {
    private FeedRepository feedRepository;

    public FeedFactory(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }

    public Feed newFeed(Integer userId, String text) {
        Feed feed = new Feed();
        feed.setFeedId(feedRepository.nextFeedId());
        Date date = new Date();
        feed.setTime(date);
        feed.setText(text);
        feed.setUserId(userId);
        feed.setType(FeedTypeEnum.text);
        feed.setStatus(FeedStatusEnum.NORMAL);
        feed.setCreateTime(date);
        feed.setUpdateTime(date);
        return feed;
    }

    public FeedEvent newPublishEvent(Feed feed) {
        FeedPublishEvent event = new FeedPublishEvent();
        event.setFeed(feed);
        event.setType(FeedEventTypeEnum.PUBLISH);
        return event;
    }
}
