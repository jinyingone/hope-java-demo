package fun.jinying.domain.feed.factory;

import fun.jinying.domain.feed.model.*;
import fun.jinying.domain.feed.repository.FeedRepository;

import javax.xml.crypto.Data;
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
        return newFeed(userId, text, FeedActionTypeEnum.ORIGIN);
    }

    public Feed newFeed(Integer userId, String text, FeedActionTypeEnum type) {
        Feed feed = new Feed();
        feed.setFeedId(feedRepository.nextFeedId());
        feed.setSourceFeedId(feed.getFeedId());
        Date date = new Date();
        feed.setTime(date);
        feed.setText(text);
        feed.setUserId(userId);
        feed.setActionType(type);
        feed.setContentType(FeedContentTypeEnum.TEXT);
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

    public FeedEvent newCreatedEvent(Feed feed) {
        FeedCreatedEvent event = new FeedCreatedEvent();
        event.setFeed(feed);
        event.setType(FeedEventTypeEnum.CREATED);
        return event;
    }

    public TimelineItem newTimelineItem(Feed feed) {
        TimelineItem item = new TimelineItem();
        item.setFeedId(feed.getFeedId());
        item.setUserId(feed.getUserId());
        item.setFeedTime(feed.getTime());
        Date date = new Date();
        item.setCreateTime(date);
        item.setUpdateTime(date);
        return item;
    }
}
