package fun.jinying.application;

import fun.jinying.domain.relation.model.Relation;
import fun.jinying.interfaces.relation.FollowCmd;

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
}
