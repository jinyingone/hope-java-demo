package fun.jinying.interfaces.relation.facade.internal;

import fun.jinying.domain.relation.model.Relation;
import fun.jinying.interfaces.relation.facade.dto.RelationDTO;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-08 10:17
 **/
public class RelationDtoAssembler {
    public static RelationDTO toDTO(Relation relation) {
        RelationDTO dto = new RelationDTO();
        dto.setFansUserId(relation.getUserId1().toString());
        dto.setFollowUserId(relation.getUserId2().toString());
        dto.setRelation(relation.getRelationU1AndU2().getValue());
        dto.setTime(relation.getFansTime());
        return dto;
    }
}
