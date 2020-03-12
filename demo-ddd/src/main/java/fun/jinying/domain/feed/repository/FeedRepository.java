package fun.jinying.domain.feed.repository;

import fun.jinying.domain.feed.model.Feed;
import fun.jinying.domain.feed.model.RepostFeed;
import fun.jinying.domain.feed.model.TimelineItem;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @description: feed存储
 * @author: sjy
 * @create: 2020-03-04 11:37
 **/
public interface FeedRepository {
    /**
     * 保存
     *
     * @param feed
     * @return
     */
    int saveFeed(Feed feed);

    /**
     * 删除feed
     *
     * @param feed
     * @return
     */
    int deleteFeed(Feed feed);

    /**
     * 生成FeedId
     *
     * @return
     */
    Long nextFeedId();

    /**
     * 保存转发feed
     *
     * @param repostFeed
     * @return
     */
    int saveRepostFeed(RepostFeed repostFeed);

    /**
     * 查询feed
     *
     * @param feedId
     * @return
     */
    Optional<Feed> getFeed(Long feedId);

    List<Feed> listFeeds(String... feedIds);

    /**
     * 保存timeline
     *
     * @param item
     * @return
     */
    int saveTimeline(TimelineItem item);

    /**
     * 列出timeline
     *
     * @param userId
     * @param fromDate
     * @param count
     * @return
     */
    List<TimelineItem> listTimeLine(Integer userId, Date fromDate, int count);

    /**
     * timeline计数
     *
     * @param logUserId
     * @return
     */
    int countTimelineItems(Integer logUserId);
}
