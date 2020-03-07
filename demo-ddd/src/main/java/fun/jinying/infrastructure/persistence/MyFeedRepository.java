package fun.jinying.infrastructure.persistence;

import fun.jinying.domain.feed.model.Feed;
import fun.jinying.domain.feed.repository.FeedRepository;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Mapper
    @Component
    public interface FeedMapper {
        @Insert("insert into feed(feed_id,user_id,text,type,status,time,create_time,update_time)" +
                "values(#{feedId},#{userId},#{text},#{type},#{status},#{time},#{createTime},#{updateTime})")
        int saveFeed(Feed feed);
    }
}
