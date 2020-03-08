package fun.jinying.interfaces.relation;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-08 14:39
 **/
@Data
public class ListFollowCmd {
    private String logUserId;
    @NotNull
    private String userId;
    @Max(0)
    private long score;
    private long time;
    @Min(1)
    @Max(1000)
    private int count;
}
