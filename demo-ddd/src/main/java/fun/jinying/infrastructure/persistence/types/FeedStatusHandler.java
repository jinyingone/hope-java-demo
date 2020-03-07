package fun.jinying.infrastructure.persistence.types;

import fun.jinying.domain.feed.model.FeedStatusEnum;
import fun.jinying.domain.shard.model.ValueEnum;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-07 10:48
 **/
public class FeedStatusHandler extends AbstractValueEnumTypeHandler<FeedStatusEnum> {
    @Override
    public ValueEnum valueOf(int value) {
        return FeedStatusEnum.NORMAL.valueOf(value);
    }
}
