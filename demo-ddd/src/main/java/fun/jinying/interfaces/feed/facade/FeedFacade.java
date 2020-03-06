package fun.jinying.interfaces.feed.facade;

import fun.jinying.interfaces.feed.PublishCmd;
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
}
