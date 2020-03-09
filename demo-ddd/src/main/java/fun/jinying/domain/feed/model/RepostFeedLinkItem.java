package fun.jinying.domain.feed.model;

import fun.jinying.domain.shard.model.Vo;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-09 23:14
 **/
@Data
@AllArgsConstructor
public class RepostFeedLinkItem implements Vo {
    private Long feedId;
    private Integer userId;
    private FeedStatusEnum statusEnum;
    private String text;
}
