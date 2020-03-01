package fun.jinying.domain.user.model;

import fun.jinying.domain.shard.model.Vo;
import lombok.Getter;

/**
 * @description: 用户事件类型
 * @author: sjy
 * @create: 2020-03-01 17:52
 **/
@Getter
public enum UserEventTypeEnum implements Vo {
    /**
     * 注册成功
     */
    REGISGERED(1),
    LOGED(2);
    ;
    private int value;

    UserEventTypeEnum(int value) {
        this.value = value;
    }
}
