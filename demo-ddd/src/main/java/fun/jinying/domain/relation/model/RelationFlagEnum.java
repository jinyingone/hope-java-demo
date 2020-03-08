package fun.jinying.domain.relation.model;

import fun.jinying.domain.shard.model.ValueEnum;
import lombok.AllArgsConstructor;

/**
 * @description: 关系
 * @author: Sjy
 * @create: 2020-03-08 09:36
 **/
@AllArgsConstructor
public enum RelationFlagEnum implements ValueEnum {
    /**
     * 无关系
     */
    NONE(0),
    /**
     * 关注
     */
    FOLLOW(1),
    /**
     * 粉丝
     */
    FANS(2),
    /**
     * 双向关注
     */
    BOTH_FOLLOW(3);

    private int value;

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public ValueEnum valueOf(int value) {
        switch (value) {
            case 0:
                return NONE;
            case 1:
                return FOLLOW;
            case 2:
                return FANS;
            case 3:
                return BOTH_FOLLOW;
            default:
                return NONE;
        }
    }
}
