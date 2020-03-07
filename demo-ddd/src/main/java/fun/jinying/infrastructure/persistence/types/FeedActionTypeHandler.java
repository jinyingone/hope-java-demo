package fun.jinying.infrastructure.persistence.types;

import fun.jinying.domain.feed.model.FeedActionTypeEnum;
import fun.jinying.domain.shard.model.ValueEnum;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-07 10:48
 **/
public class FeedActionTypeHandler extends AbstractValueEnumTypeHandler<FeedActionTypeEnum> {
    @Override
    public ValueEnum valueOf(int value) {
        return FeedActionTypeEnum.ORIGIN.valueOf(value);
    }
}
