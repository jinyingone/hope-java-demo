package fun.jinying.interfaces.relation;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description: 关注
 * @author: sjy
 * @create: 2020-03-08 10:13
 **/
@Data
public class FollowCmd {
    @NotNull
    private String followUserId;
    private String fansUserId;
}
