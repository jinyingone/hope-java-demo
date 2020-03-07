package fun.jinying.interfaces.feed;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-07 11:41
 **/
@Data
public class RepostCmd {
    @NotNull
    private String feedId;
    private String text;
    private Integer logUserId;
}
