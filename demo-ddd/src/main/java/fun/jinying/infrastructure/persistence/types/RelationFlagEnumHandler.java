package fun.jinying.infrastructure.persistence.types;

import fun.jinying.domain.relation.model.RelationFlagEnum;
import fun.jinying.domain.shard.model.ValueEnum;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-07 10:48
 **/
public class RelationFlagEnumHandler extends AbstractValueEnumTypeHandler<RelationFlagEnum> {
    @Override
    public ValueEnum valueOf(int value) {
        return RelationFlagEnum.NONE.valueOf(value);
    }
}
