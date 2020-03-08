package fun.jinying.interfaces.relation.facade;

import fun.jinying.interfaces.relation.FollowCmd;
import fun.jinying.interfaces.relation.facade.dto.RelationDTO;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-08 10:11
 **/
public interface RelationFacade {
    /**
     * 关注
     *
     * @param followCmd
     * @return
     */
    RelationDTO follow(FollowCmd followCmd);
}
