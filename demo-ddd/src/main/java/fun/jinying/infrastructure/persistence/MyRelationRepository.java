package fun.jinying.infrastructure.persistence;

import fun.jinying.domain.relation.model.Relation;
import fun.jinying.domain.relation.model.RelationFlagEnum;
import fun.jinying.domain.relation.repository.RelationRepository;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

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
    public int saveFansRelation(Relation relation) {
        return relationMapper.insertOrUpdate(relation);
    }

    @Override
    public int saveFollowRelation(Relation relation) {
        return relationMapper.insertOrUpdate(relation);
    }

    @Override
    public List<Relation> listFans(String userId, Date date) {
        return relationMapper.selectFans(userId, RelationFlagEnum.FANS, date);
    }

    @Override
    public int countFans(String userId) {
        return relationMapper.countFans(userId, RelationFlagEnum.FANS);
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

        /**
         * 查询粉丝
         *
         * @param userId
         * @return
         */
        @Select("select * from relation where user_id1=#{userId} and fans_flag=#{relation} and fans_time<#{date}order by fans_time desc")
        List<Relation> selectFans(@Param("userId") String userId, @Param("relation") RelationFlagEnum relation, @Param("date") Date date);

        /**
         * 粉丝计数
         *
         * @param userId
         * @return
         */
        @Select("select count(*) from relation where user_id1=#{userId} and fans_flag=#{relation}")
        int countFans(@Param("userId") String userId, @Param("relation") RelationFlagEnum relation);
    }
}
