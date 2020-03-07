package fun.jinying.infrastructure.persistence.types;

import fun.jinying.domain.feed.model.FeedTypeEnum;
import fun.jinying.domain.shard.model.ValueEnum;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-07 10:48
 **/
public class FeedTypeHandler extends AbstractValueEnumTypeHandler<FeedTypeEnum> {
    @Override
    public ValueEnum valueOf(int value) {
        return FeedTypeEnum.text.valueOf(value);
    }
}
