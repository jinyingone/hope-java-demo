package fun.jinying.interfaces.feed.facade;

import fun.jinying.interfaces.common.PageAndList;
import fun.jinying.interfaces.feed.ListTimelineQuery;
import fun.jinying.interfaces.feed.PublishCmd;
import fun.jinying.interfaces.feed.RepostCmd;
import fun.jinying.interfaces.feed.facade.dto.FeedDTO;

/**
 * @description: feed
 * @author: sjy
 * @create: 2020-03-06 22:24
 **/
public interface FeedFacade {
    /**
     * 发布
     *
     * @param publishCmd
     * @return
     */
    FeedDTO publish(PublishCmd publishCmd);

    /**
     * 转发
     *
     * @param repostCmd
     * @return
     */
    FeedDTO repost(RepostCmd repostCmd);

    /**
     * timeline
     *
     * @param cmd
     * @return
     */
    PageAndList listTimeline(ListTimelineQuery cmd);
}
