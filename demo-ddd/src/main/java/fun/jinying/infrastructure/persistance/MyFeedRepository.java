package fun.jinying.infrastructure.persistance;

import fun.jinying.domain.feed.model.Feed;
import fun.jinying.domain.feed.repository.FeedRepository;
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

    @Override
    public int saveFeed(Feed feed) {
        return 0;
    }

    @Override
    public int deleteFeed(Feed feed) {
        return 0;
    }

    @Override
    public Long nextFeedId() {
        return feedIdGen.incrementAndGet();
    }
}
