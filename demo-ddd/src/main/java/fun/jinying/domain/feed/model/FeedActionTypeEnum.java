package fun.jinying.domain.feed.model;

import fun.jinying.domain.shard.model.ValueEnum;
import lombok.Getter;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-04 11:32
 **/
@Getter
public enum FeedActionTypeEnum implements ValueEnum {
    /**
     * 原创
     */
    ORIGIN(1),
    /**
     * 转发
     */
    REPOST(2);
    private int value;

    FeedActionTypeEnum(int value) {
        this.value = value;
    }

    @Override
    public FeedActionTypeEnum valueOf(int value) {
        switch (value) {
            case 1:
                return ORIGIN;
            case 2:
                return REPOST;
            default:
                return ORIGIN;
        }
    }
}
