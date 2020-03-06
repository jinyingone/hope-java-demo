package fun.jinying.domain.feed.model;

import fun.jinying.domain.shard.model.Event;
import lombok.Data;

/**
 * @description: feed事件
 * @author: sjy
 * @create: 2020-03-06 23:08
 **/
@Data
public class FeedEvent implements Event {
    private Feed feed;
    private FeedEventTypeEnum type;
}
