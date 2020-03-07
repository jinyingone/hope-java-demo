package fun.jinying.domain.feed.repository;

import fun.jinying.domain.feed.model.Feed;

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
}