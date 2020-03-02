package fun.jinying.domain.user.model;

import fun.jinying.domain.shard.model.Vo;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description: 用户更新器
 * @author: sjy
 * @create: 2020-03-02 22:10
 **/
@AllArgsConstructor
@Data
public class UserUpdater implements Vo {
    private String userName;
    private String avatar;
    private String phone;
}
