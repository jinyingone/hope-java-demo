package fun.jinying.interfaces.feed;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description: 发布命令
 * @author: sjy
 * @create: 2020-03-06 21:51
 **/
@Data
public class PublishCmd {
    @NotNull
    private String text;
    private Integer logUserId;
}
