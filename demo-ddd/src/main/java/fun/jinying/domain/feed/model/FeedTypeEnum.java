package fun.jinying.domain.feed.model;

import fun.jinying.domain.shard.model.ValueEnum;
import fun.jinying.domain.shard.model.Vo;
import lombok.Getter;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-04 11:32
 **/
@Getter
public enum FeedTypeEnum implements ValueEnum {
    /**
     * 文本
     */
    text(1);
    private int value;

    FeedTypeEnum(int value) {
        this.value = value;
    }

    @Override
    public FeedTypeEnum valueOf(int value) {
        switch (value) {
            case 1:
                return text;
            default:
                return text;
        }
    }
}