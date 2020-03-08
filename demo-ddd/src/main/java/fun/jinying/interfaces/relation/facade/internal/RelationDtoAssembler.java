package fun.jinying.interfaces.relation.facade.internal;

import fun.jinying.domain.relation.model.Relation;
import fun.jinying.domain.relation.model.RelationFlagEnum;
import fun.jinying.interfaces.relation.facade.dto.RelationDTO;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-08 10:17
 **/
public class RelationDtoAssembler {
    public RelationDTO toDTO(Relation relation) {
        RelationDTO dto = new RelationDTO();
        dto.setFansUserId(relation.getFansUserId().toString());
        dto.setFollowUserId(relation.getFollowUserId().toString());
        dto.setFlag(relation.getRelationFlag(RelationFlagEnum.FANS));
        dto.setTime(relation.getFansTime());
        return dto;
    }
}
