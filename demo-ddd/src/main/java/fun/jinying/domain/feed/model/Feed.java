package fun.jinying.domain.feed.model;

import fun.jinying.domain.shard.model.Entity;
import lombok.Data;

import java.util.Date;

/**
 * @description: 订阅源
 * @author: sjy
 * @create: 2020-02-26 22:01
 **/
@Data
public class Feed implements Entity {
    private Long feedId;
    private Integer userId;
    private String text;
    private FeedTypeEnum type;
    private FeedStatusEnum status;
    private Date time;
    private Date createTime;
    private Date updateTime;

    public void repost() {
    }

    public void post() {
    }

}
