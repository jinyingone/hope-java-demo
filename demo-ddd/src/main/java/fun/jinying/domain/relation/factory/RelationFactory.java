package fun.jinying.domain.relation.factory;

import fun.jinying.domain.relation.model.Relation;
import fun.jinying.domain.relation.model.RelationFlagEnum;

import java.util.Date;

/**
 * @description: 关系工厂
 * @author: sjy
 * @create: 2020-03-08 10:23
 **/
public class RelationFactory {
    public Relation newFansRelation(Integer fans, Integer follow) {
        Relation relation = new Relation();
        Date date = new Date();
        relation.setUserId1(fans);
        relation.setUserId2(follow);
        relation.setFansTime(date);
        relation.setFansTime(date);
        relation.setFollowTime(date);
        relation.setFansTime(date);
        relation.setCreateTime(date);
        relation.setUpdateTime(date);
        relation.setFansFlag(RelationFlagEnum.FANS);
        return relation;
    }

    public Relation newFollowRelation(Integer fans, Integer follow) {
        Relation relation = new Relation();
        Date date = new Date();
        relation.setUserId1(fans);
        relation.setUserId2(follow);
        relation.setFansTime(date);
        relation.setFansTime(date);
        relation.setFollowTime(date);
        relation.setFansTime(date);
        relation.setCreateTime(date);
        relation.setUpdateTime(date);
        relation.setFollowFlag(RelationFlagEnum.FOLLOW);

        return relation;
    }

    /**
     * 创建一个空关系
     *
     * @param userId1
     * @param userId2
     * @return
     */
    public Relation newNonRelation(Integer userId1, Integer userId2) {
        Relation relation = new Relation();
        relation.setUserId1(userId1);
        relation.setUserId2(userId2);
        relation.setFansFlag(RelationFlagEnum.NONE);
        relation.setFollowFlag(RelationFlagEnum.NONE);
        return relation;
    }
}
