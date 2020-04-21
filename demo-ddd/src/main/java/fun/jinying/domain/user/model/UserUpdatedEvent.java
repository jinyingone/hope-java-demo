package fun.jinying.domain.user.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @description: 用户更新
 * @author: sjy
 * @create: 2020-03-01 23:21
 **/
@Data
public class UserUpdatedEvent extends UserEvent {
    private Map<String,Object> updatedFields;
}
