package fun.jinying.interfaces.relation.facade;

import fun.jinying.interfaces.common.PageAndList;
import fun.jinying.interfaces.relation.FollowCmd;
import fun.jinying.interfaces.relation.ListFansCmd;
import fun.jinying.interfaces.relation.ListFollowCmd;
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
    PageAndList listFans(ListFansCmd cmd);

    /**
     * 关注列表
     *
     * @param cmd
     * @return
     */
    PageAndList listFollow(ListFollowCmd cmd);

    /**
     * 查询用户关系
     *
     * @param userId1
     * @param userId2
     * @return
     */
    RelationDTO getRelation(Integer userId1, Integer userId2);
}
