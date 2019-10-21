package fun.jinying;

import java.util.Collections;
import java.util.List;

/**
 * 常见的流错误
 *
 * @author jy
 * @date 2019-10-21 下午5:03
 **/
public class StreamError {
    /**
     * 集合为空,对返回的结果直接get,会报异常
     *
     * @throws NoSuchFieldException
     */
    public static void StreamsEmpty() {
        List<Integer> list = Collections.emptyList();
        Integer integer = list.stream().max(Integer::compareTo).get();
        System.out.println(integer);
    }
}
