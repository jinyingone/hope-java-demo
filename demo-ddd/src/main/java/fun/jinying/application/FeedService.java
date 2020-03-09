package fun.jinying.application;

import fun.jinying.domain.feed.model.Feed;
import fun.jinying.domain.feed.model.FeedEvent;
import fun.jinying.interfaces.feed.ListTimelineCmd;
import fun.jinying.interfaces.feed.PublishCmd;
import fun.jinying.interfaces.feed.RepostCmd;

import java.util.List;

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

    /**
     * 根据feed事件保存timeline
     *
     * @param feedEvent feed事件
     */
    void saveTimeLine(FeedEvent feedEvent);

    /**
     * 列出timeline
     *
     * @param cmd 请求命令
     * @return feed列表
     */
    List<Feed> listTimeline(ListTimelineCmd cmd);

    /**
     * 最新的feed数量
     *
     * @param cmd
     * @return
     */
    int countTimeline(ListTimelineCmd cmd);
}
