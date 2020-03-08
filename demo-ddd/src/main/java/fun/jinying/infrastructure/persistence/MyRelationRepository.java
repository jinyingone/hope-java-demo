package fun.jinying.infrastructure.persistence;

import fun.jinying.domain.relation.model.Relation;
import fun.jinying.domain.relation.repository.RelationRepository;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 关系存储实现
 * @author: sjy
 * @create: 2020-03-08 10:32
 **/
@Component
public class MyRelationRepository implements RelationRepository {
    @Autowired
    private RelationMapper relationMapper;

    @Override
    public int saveRelation(Relation relation) {
        return relationMapper.insertOrUpdate(relation);
    }

    @Mapper
    @Component
    public interface RelationMapper {
        /**
         * 保存
         *
         * @param relation
         * @return
         */
        int insertOrUpdate(Relation relation);
    }
}
