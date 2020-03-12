package fun.jinying.application;

import fun.jinying.domain.feed.model.Feed;
import fun.jinying.domain.feed.model.FeedEvent;
import fun.jinying.interfaces.feed.PublishCmd;
import fun.jinying.interfaces.feed.RepostCmd;

/**
 * @description: feed
 * @author: sjy
 * @create: 2020-03-06 22:30
 **/
public interface FeedService {
    /**
     * 发布feed
     *
     * @param feed feed
     * @return
     */
    Feed publish(PublishCmd feed);

    /**
     * 根据事件发布
     *
     * @param feedEvent feed事件
     * @return feed
     */
    Feed publish(FeedEvent feedEvent);

    /**
     * 转发
     *
     * @param repostCmd 转发命令
     * @return feed
     */
    Feed repost(RepostCmd repostCmd);

}
