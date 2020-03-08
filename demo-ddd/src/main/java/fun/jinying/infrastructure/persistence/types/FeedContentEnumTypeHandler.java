package fun.jinying.infrastructure.persistence.types;

import fun.jinying.domain.feed.model.FeedContentTypeEnum;
import fun.jinying.domain.shard.model.ValueEnum;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-07 10:48
 **/
public class FeedContentEnumTypeHandler extends AbstractValueEnumTypeHandler<FeedContentTypeEnum> {
    @Override
    public ValueEnum valueOf(int value) {
        return FeedContentTypeEnum.TEXT.valueOf(value);
    }
}
