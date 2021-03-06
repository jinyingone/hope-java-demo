package fun.jinying;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * 常见的null错误
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

    /**
     * 包装类型自动拆箱
     * 推荐pojo尽量使用包装类
     */
    public void autoUnBox() {
        User user = new User();
        Student student = new Student();
        student.setId(user.getId());
    }

    @Data
    private class User {
        Long id;
    }

    @Data
    private class Student {
        long id;
    }
}
