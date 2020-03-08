package fun.jinying.interfaces.relation.facade;

import fun.jinying.interfaces.relation.FollowCmd;
import fun.jinying.interfaces.relation.ListFansCmd;
import fun.jinying.interfaces.relation.facade.dto.PageAndListDTO;
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

    /**
     * 粉丝列表
     *
     * @param cmd
     * @return
     */
    PageAndListDTO listFans(ListFansCmd cmd);
}
