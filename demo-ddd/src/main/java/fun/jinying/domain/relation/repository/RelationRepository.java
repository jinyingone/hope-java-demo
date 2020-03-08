package fun.jinying.domain.relation.repository;

import fun.jinying.domain.relation.model.Relation;

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
    int saveRelation(Relation relation);
}
