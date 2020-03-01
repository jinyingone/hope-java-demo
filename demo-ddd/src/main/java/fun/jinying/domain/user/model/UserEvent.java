package fun.jinying.domain.user.model;

import fun.jinying.domain.shard.model.Event;
import lombok.Data;

/**
 * @description: 用户事件
 * @author: sjy
 * @create: 2020-03-01 17:12
 **/
@Data
public class UserEvent implements Event {
    private UserEventTypeEnum type;
    private String userId;
    private Long timestamp = System.currentTimeMillis();
}
