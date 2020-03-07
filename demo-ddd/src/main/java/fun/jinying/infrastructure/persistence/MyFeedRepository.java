package fun.jinying.infrastructure.persistence;

import fun.jinying.domain.feed.RepostFeed;
import fun.jinying.domain.feed.model.Feed;
import fun.jinying.domain.feed.model.FeedActionTypeEnum;
import fun.jinying.domain.feed.model.FeedStatusEnum;
import fun.jinying.domain.feed.repository.FeedRepository;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @description: feed存储实现
 * @author: sjy
 * @create: 2020-03-04 11:39
 **/
@Component
public class MyFeedRepository implements FeedRepository {
    AtomicLong feedIdGen = new AtomicLong(1000);
    @Autowired
    private FeedMapper feedMapper;

    @Override
    public int saveFeed(Feed feed) {
        return feedMapper.saveFeed(feed);
    }

    @Override
    public int deleteFeed(Feed feed) {
        return 0;
    }

    @Override
    public Long nextFeedId() {
        return feedIdGen.incrementAndGet();
    }

    @Override
    public int saveRepostFeed(RepostFeed repostFeed) {
        return feedMapper.saveRepostFeed(repostFeed);
    }

    @Override
    public Optional<Feed> getFeed(Long feedId) {
        Feed feed = feedMapper.getFeed(feedId);
        if (feed != null && FeedActionTypeEnum.REPOST == feed.getActionType()) {
            RepostFeed repostFeed = feedMapper.getRepostFeed(feedId);
            feed.setRepostFeed(repostFeed);
        }
        return Optional.ofNullable(feed);
    }

    @Mapper
    @Component
    public interface FeedMapper {
        /**
         * 保存主feed
         *
         * @param feed
         * @return
         */
        @Insert("insert into feed(feed_id,source_feed_id,user_id,text,action_type,content_type,status,time,create_time,update_time)" +
                "values(#{feedId},#{sourceFeedId},#{userId},#{text},#{actionType},#{contentType},#{status},#{time},#{createTime},#{updateTime})")
        int saveFeed(Feed feed);

        /**
         * 保存转发
         *
         * @param repostFeed
         * @return
         */
        @Insert("insert into repost_feed(feed_id,user_id,status,reposted_feed_id,source_feed_id,feed_link,time,create_time,update_time)" +
                "values(#{feedId},#{userId},#{status},#{repostedFeedId},#{sourceFeedId},#{feedLink},#{time},#{createTime},#{updateTime})")
        int saveRepostFeed(RepostFeed repostFeed);

        /**
         * 查询feed
         *
         * @param feedId
         * @return
         */
        @Select("select * from feed where feed_id=#{feedId}")
        Feed getFeed(Long feedId);

        @Select("select * from repost_feed where feed_id=#{feedId}")
        RepostFeed getRepostFeed(Long feedId);
    }
}
