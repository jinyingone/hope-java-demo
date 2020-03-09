package fun.jinying.interfaces.feed.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-06 22:25
 **/
@Data
public class FeedDTO {
    public String feedId;
    private String userId;
    private int status;
    public String text;
    public Long time;
    public int actionType;
    public int contentType;
    private RepostFeedDTO repost;
}
