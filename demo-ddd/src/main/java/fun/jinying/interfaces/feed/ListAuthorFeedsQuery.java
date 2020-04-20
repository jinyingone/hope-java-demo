package fun.jinying.interfaces.feed;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-07 11:41
 **/
@Data
public class ListAuthorFeedsQuery {
    @Max(0)
    private long score;
    @Min(0)
    @Max(1000)
    private int count;
    private String logUserId;
}
