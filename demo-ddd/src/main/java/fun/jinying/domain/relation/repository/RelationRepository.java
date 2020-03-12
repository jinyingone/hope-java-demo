package fun.jinying.domain.relation.repository;

import fun.jinying.domain.relation.model.Relation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @description: 关系存储
 * @author: sjy
 * @create: 2020-03-08 10:23
 **/
public interface RelationRepository {
    /**
     * 保存粉丝关系
     *
     * @param relation
     * @return
     */
    int saveFansRelation(Relation relation);

    /**
     * 保存关注关系
     *
     * @param followRelation
     * @return
     */
    int saveFollowRelation(Relation followRelation);

    /**
     * 粉丝列表
     *
     * @param userId
     * @param date
     * @return
     */
    List<Relation> listFans(Integer userId, Date date);

    /**
     * 粉丝计数
     *
     * @param userId
     * @return
     */
    int countFans(Integer userId);

    /**
     * 关注列表
     *
     * @param userId
     * @param date
     * @return
     */
    List<Relation> listFollow(Integer userId, Date date);

    /**
     * 关注计数
     *
     * @param userId
     * @return
     */
    int countFollow(Integer userId);

    /**
     * 获取两个用户的关系
     *
     * @param userId1
     * @param userId2
     * @return
     */
    Optional<Relation> getByUserId1AndUserId2(Integer userId1, Integer userId2);
}
