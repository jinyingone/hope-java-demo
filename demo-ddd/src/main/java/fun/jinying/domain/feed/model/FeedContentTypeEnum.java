package fun.jinying.domain.feed.model;

import fun.jinying.domain.shard.model.ValueEnum;
import lombok.Getter;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-04 11:32
 **/
@Getter
public enum FeedContentTypeEnum implements ValueEnum {
    /**
     * 文本
     */
    TEXT(1),
    /**
     * 图片
     */
    PIC(2);
    private int value;

    FeedContentTypeEnum(int value) {
        this.value = value;
    }

    @Override
    public FeedContentTypeEnum valueOf(int value) {
        switch (value) {
            case 1:
                return TEXT;
            case 2:
                return PIC;
            default:
                return TEXT;
        }
    }
}
