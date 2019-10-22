package fun.jinying;

import java.util.Collections;
import java.util.List;

/**
 * 常见的流错误
 *
 * @author jy
 * @date 2019-10-21 下午5:03
 **/
public class NullError {
    /**
     * 集合为空,对返回的结果直接get,会报异常
     *
     * @throws NoSuchFieldException
     */
    public void StreamsEmpty() {
        List<Integer> list = Collections.emptyList();
        Integer integer = list.stream().max(Integer::compareTo).get();
        System.out.println(integer);
    }

    /**
     * 从缓存,数据库等外部数据源查询出来的数据,就有可能为空
     */
    public void dataEmpty() {
        String fromDB = getFromDB("123");
        System.out.println(fromDB.length());
    }

    private String getFromDB(String id) {
        return null;
    }
}
