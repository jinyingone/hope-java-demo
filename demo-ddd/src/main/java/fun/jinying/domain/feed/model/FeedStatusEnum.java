package fun.jinying.domain.feed.model;

import fun.jinying.domain.shard.model.ValueEnum;
import lombok.Getter;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-04 11:32
 **/
@Getter
public enum FeedStatusEnum implements ValueEnum {
    NORMAL(1);
    private int value;

    FeedStatusEnum(int value) {
        this.value = value;
    }

    @Override
    public FeedStatusEnum valueOf(int value) {
        switch (value) {
            case 1:
                return NORMAL;
            default:
                return NORMAL;
        }
    }
}
