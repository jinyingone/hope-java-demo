package fun.jinying.interfaces.relation.facade.dto;

import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-08 10:11
 **/
@Data
public class RelationDTO {
    private String fansUserId;
    private String followUserId;
    private int relation;
    private Date time;

    public static Long getScore(RelationDTO dto) {
        return -dto.getTime().getTime();
    }
}
