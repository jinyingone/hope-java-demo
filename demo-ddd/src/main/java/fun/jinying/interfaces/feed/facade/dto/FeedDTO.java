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
@AllArgsConstructor
@NoArgsConstructor
public class FeedDTO {
    public String feedId;
    private String userId;
    public String text;
    public Long time;

    public FeedDTO(String userId, String text) {
        this.userId = userId;
        this.text = text;
    }
}
