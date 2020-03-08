package fun.jinying.domain.feed.model;

import fun.jinying.domain.shard.model.Vo;
import lombok.Getter;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-04 11:32
 **/
@Getter
public enum FeedEventTypeEnum implements Vo {
    /**
     * 发布
     */
    PUBLISH(1),
    /**
     * 创建
     */
    CREATED(2);

    private int value;

    FeedEventTypeEnum(int value) {
        this.value = value;
    }
}
