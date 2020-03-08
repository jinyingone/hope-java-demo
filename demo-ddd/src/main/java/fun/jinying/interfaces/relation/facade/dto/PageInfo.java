package fun.jinying.interfaces.relation.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-08 14:42
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo {
    private long score;
    private int totalCount;
    private boolean more;
}
