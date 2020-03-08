package fun.jinying.domain.feed.model;

import fun.jinying.domain.shard.model.Vo;
import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-08 22:33
 **/
@Data
public class TimelineItem implements Vo {
    private Long feedId;
    private Integer userId;
    private Date feedTime;
    private Date createTime;
    private Date updateTime;
}
