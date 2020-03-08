package fun.jinying.interfaces.relation.facade.dto;

import fun.jinying.domain.relation.model.RelationFlagEnum;
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
    private RelationFlagEnum flag;
    private Date time;
}
