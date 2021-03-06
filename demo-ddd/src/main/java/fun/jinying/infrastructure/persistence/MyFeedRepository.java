package fun.jinying.infrastructure.persistence;

import fun.jinying.domain.feed.model.Feed;
import fun.jinying.domain.feed.model.FeedActionTypeEnum;
import fun.jinying.domain.feed.model.RepostFeed;
import fun.jinying.domain.feed.model.TimelineItem;
import fun.jinying.domain.feed.repository.FeedRepository;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    @Autowired
    private RepostMapper repostMapper;
    @Autowired
    private TimelineMapper timelineMapper;

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
        return repostMapper.saveRepostFeed(repostFeed);
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

    @Override
    public List<Feed> listFeeds(String... feedIds) {
        List<Feed> feeds = feedMapper.listFeeds(feedIds);
        List<Feed> result = new ArrayList<>(feedIds.length);
        for (String feedId : feedIds) {
            boolean match = false;
            for (Feed feed : feeds) {
                if (feedId.equals(feed.getFeedId().toString())) {
                    result.add(feed);
                    match = true;
                    break;
                }
            }
            if (!match) {
                result.add(null);
            }
        }
        return result;
    }

    @Override
    public int saveTimeline(TimelineItem item) {
        return timelineMapper.saveTimeline(item);
    }

    @Override
    public List<TimelineItem> listTimeLine(Integer userId, Date fromDate, int count) {
        return timelineMapper.listTimeLine(userId, fromDate, count);
    }

    @Override
    public int countTimelineItems(Integer logUserId) {
        return timelineMapper.count(logUserId);
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
                "values(#{feedId},#{sourceFeedId},#{userId},#{text},#{actionType},#{contentType},#{status},#{time},#{createTime},#{updateTime}) " +
                "on duplicate key update status=values(status),update_time=values(update_time)")
        int saveFeed(Feed feed);

        /**
         * 查询feed
         *
         * @param feedId
         * @return
         */
        @Select("select * from feed where feed_id=#{feedId}")
        Feed getFeed(Long feedId);

        List<Feed> listFeeds(@Param("feedIds") String[] feedIds);

        @Select("select * from repost_feed where feed_id=#{feedId}")
        RepostFeed getRepostFeed(Long feedId);


    }

    @Mapper
    @Component
    public interface RepostMapper {
        /**
         * 保存转发
         *
         * @param repostFeed
         * @return
         */
        @Insert("insert into repost_feed(feed_id,user_id,status,reposted_feed_id,source_feed_id,feed_link,time,create_time,update_time)" +
                "values(#{feedId},#{userId},#{status},#{repostedFeedId},#{sourceFeedId},#{feedLink},#{time},#{createTime},#{updateTime})")
        int saveRepostFeed(RepostFeed repostFeed);
    }

    @Mapper
    @Component
    public interface TimelineMapper {
        @Insert("insert into timeline (user_id,feed_id,feed_time,create_time,update_time) " +
                "values(#{userId},#{feedId},#{feedTime},#{createTime},#{updateTime}) " +
                "on duplicate key update feed_time=values(feed_time),update_time=values(update_time)")
        int saveTimeline(TimelineItem item);

        @Select("select * from timeline where user_id=#{userId} and feed_time < #{feedTime} order by feed_time desc limit #{count}")
        List<TimelineItem> listTimeLine(@Param("userId") Integer userId, @Param("feedTime") Date feedTime, @Param("count") int count);

        @Select("select count(*) from timeline where user_id=#{userId}")
        int count(@Param("userId") Integer userId);
    }
}
