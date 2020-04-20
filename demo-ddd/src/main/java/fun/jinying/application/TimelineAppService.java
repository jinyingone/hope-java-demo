package fun.jinying.application;

import fun.jinying.domain.feed.model.Feed;
import fun.jinying.domain.feed.model.FeedEvent;
import fun.jinying.interfaces.feed.ListTimelineQuery;

import java.util.List;

/**
 * @description: timeline服务
 * @author: sjy
 * @create: 2020-03-12 11:52
 **/
public interface TimelineAppService {
    /**
     * 列出timeline
     *
     * @param cmd 请求命令
     * @return feed列表
     */
    List<Feed> listTimeline(ListTimelineQuery cmd);

    /**
     * 最新的feed数量
     *
     * @param cmd
     * @return
     */
    int countTimeline(ListTimelineQuery cmd);

    /**
     * 根据feed事件保存timeline
     *
     * @param feedEvent feed事件
     */
    void saveTimeLine(FeedEvent feedEvent);
}
