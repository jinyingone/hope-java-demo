package fun.jinying.interfaces.common;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-09 21:38
 **/
@Data
public class PageAndList<T, R> {
    private List<R> list;
    private PageInfo pageInfo;

    public void init(int totalCount, List<T> listData, Function<T, R> t2rFunction, Function<R, Long> scoreFunction, long defaultScore) {
        List<R> list = listData.stream().map(t2rFunction).collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotalCount(totalCount);
        pageInfo.setMore(!list.isEmpty());
        R r = CollectionUtils.lastElement(list);
        pageInfo.setScore(r != null ? scoreFunction.apply(r) : defaultScore);
        this.list = list;
        this.pageInfo = pageInfo;
    }

}
