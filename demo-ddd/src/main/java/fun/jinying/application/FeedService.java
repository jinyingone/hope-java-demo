package fun.jinying.application;

import fun.jinying.domain.feed.model.Feed;
import fun.jinying.domain.feed.model.FeedEvent;
import fun.jinying.interfaces.feed.PublishCmd;

/**
 * @description: feed
 * @author: sjy
 * @create: 2020-03-06 22:30
 **/
public interface FeedService {
    /**
     * 发布feed
     *
     * @param feed
     * @return
     */
    Feed publish(PublishCmd feed);

    /**
     * 根据事件发布
     *
     * @param feedEvent
     */
    Feed publish(FeedEvent feedEvent);
}
