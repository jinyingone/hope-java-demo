package fun.jinying.domain.shard.model;

/**
 * @description: value类型的枚举
 * @author: sjy
 * @create: 2020-03-07 10:56
 **/
public interface ValueEnum extends Vo {
    /**
     * 获取值
     *
     * @return
     */
    int getValue();

    /**
     * 值转枚举
     *
     * @param value
     * @return
     */
    ValueEnum valueOf(int value);
}
