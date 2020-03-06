package fun.jinying.domain.feed.model;

import fun.jinying.domain.shard.model.Vo;
import lombok.Getter;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-04 11:32
 **/
@Getter
public enum FeedTypeEnum implements Vo {
    /**
     * 文本
     */
    text(1);
    private int value;

    FeedTypeEnum(int value) {
        this.value = value;
    }
}
