package fun.jinying.domain.feed;

import fun.jinying.domain.feed.model.FeedStatusEnum;
import lombok.Data;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * @description: 转发feed
 * @author: sjy
 * @create: 2020-03-07 16:03
 **/
@Data
public class RepostFeed {
    private Long feedId;
    private Integer userId;
    private FeedStatusEnum status;
    /**
     * 被转发的feedId
     */
    private Long repostedFeedId;
    private List<String> feedLink;
    /**
     * 源feedId
     */
    private Long sourceFeedId;
    private Date time;
    private Date createTime;
    private Date updateTime;
}
