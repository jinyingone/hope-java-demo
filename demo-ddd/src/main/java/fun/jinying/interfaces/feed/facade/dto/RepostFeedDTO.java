package fun.jinying.interfaces.feed.facade.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: 转发
 * @author: sjy
 * @create: 2020-03-09 22:37
 **/
@Data
@NoArgsConstructor
public class RepostFeedDTO {
    private String feedId;
    private String repostedFeedId;
    private String text;
    private int status;
    private List<RepostFeedDTO> feedLink;

    public RepostFeedDTO(String feedId, String repostedFeedId, String text, int status) {
        this.feedId = feedId;
        this.repostedFeedId = repostedFeedId;
        this.text = text;
        this.status = status;
    }
}
