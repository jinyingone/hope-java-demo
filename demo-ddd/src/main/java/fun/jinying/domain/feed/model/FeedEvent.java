package fun.jinying.domain.feed.model;

import fun.jinying.domain.shard.model.DomainEvent;
import lombok.Data;

/**
 * @description: feed事件
 * @author: sjy
 * @create: 2020-03-06 23:08
 **/
@Data
public class FeedEvent implements DomainEvent {
    private Feed feed;
    private FeedEventTypeEnum type;
}
