package fun.jinying.application;

import fun.jinying.domain.relation.model.Relation;
import fun.jinying.interfaces.relation.FollowCmd;
import fun.jinying.interfaces.relation.ListFansCmd;

import java.util.List;

/**
 * @description: 关系
 * @author: sjy
 * @create: 2020-03-08 10:20
 **/
public interface RelationService {
    /**
     * 关注
     *
     * @param followCmd
     * @return
     */
    Relation follow(FollowCmd followCmd);

    /**
     * 粉丝
     *
     * @param cmd
     * @return
     */
    List<Relation> listFans(ListFansCmd cmd);

    /**
     * 粉丝计数
     *
     * @param userId
     * @return
     */
    int countFans(String userId);
}
