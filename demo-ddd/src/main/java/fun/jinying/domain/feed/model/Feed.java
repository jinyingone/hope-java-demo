package fun.jinying.domain.feed.model;

import fun.jinying.domain.feed.RepostFeed;
import fun.jinying.domain.shard.model.Entity;
import lombok.Data;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @description: 订阅源
 * @author: sjy
 * @create: 2020-02-26 22:01
 **/
@Data
public class Feed implements Entity {
    private Long feedId;
    private Long sourceFeedId;
    private Integer userId;
    private String text;
    private FeedActionTypeEnum actionType;
    private FeedContentTypeEnum contentType;
    private FeedStatusEnum status;
    private Date time;
    private Date createTime;
    private Date updateTime;
    private RepostFeed repostFeed;


    public RepostFeed repost(Feed repostedFeed) {
        this.setSourceFeedId(repostedFeed.getSourceFeedId());

        RepostFeed newFeed = new RepostFeed();
        newFeed.setFeedId(this.getFeedId());
        newFeed.setRepostedFeedId(repostedFeed.getFeedId());
        newFeed.setSourceFeedId(repostedFeed.getSourceFeedId());
        newFeed.setUserId(this.getUserId());
        newFeed.setCreateTime(this.getCreateTime());
        newFeed.setUpdateTime(this.getUpdateTime());
        newFeed.setTime(this.getTime());
        newFeed.setStatus(FeedStatusEnum.NORMAL);
        if (repostedFeed.getActionType() == FeedActionTypeEnum.REPOST) {
            List<String> feedLink = repostedFeed.getRepostFeed().getFeedLink();
            feedLink.add(0, repostedFeed.getFeedId().toString());
            newFeed.setFeedLink(feedLink);
        } else {
            newFeed.setFeedLink(Collections.singletonList(repostedFeed.getFeedId().toString()));
        }
        return newFeed;
    }

    public void post() {
    }

}
