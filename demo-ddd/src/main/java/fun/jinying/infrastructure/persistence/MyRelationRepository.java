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
import java.util.Optional;

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
    public List<Relation> listFans(Integer userId, Date date) {
        return relationMapper.selectFans(userId, RelationFlagEnum.FANS, date);
    }

    @Override
    public int countFans(Integer userId) {
        return relationMapper.countFans(userId, RelationFlagEnum.FANS);
    }

    @Override
    public List<Relation> listFollow(Integer userId, Date date) {
        return relationMapper.selectFollow(userId, RelationFlagEnum.FOLLOW, date);
    }

    @Override
    public int countFollow(Integer userId) {
        return relationMapper.countFollow(userId, RelationFlagEnum.FOLLOW);
    }

    @Override
    public Optional<Relation> getByUserId1AndUserId2(Integer userId1, Integer userId2) {
        Relation relation = relationMapper.getByUserId1AndUserId2(userId1, userId2);
        return Optional.ofNullable(relation);
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
        List<Relation> selectFans(@Param("userId") Integer userId, @Param("relation") RelationFlagEnum relation, @Param("date") Date date);

        /**
         * 粉丝计数
         *
         * @param userId
         * @return
         */
        @Select("select count(*) from relation where user_id1=#{userId} and fans_flag=#{relation}")
        int countFans(@Param("userId") Integer userId, @Param("relation") RelationFlagEnum relation);

        @Select("select * from relation where user_id1=#{userId} and follow_flag=#{relation} and follow_time<#{date}order by follow_time desc")
        List<Relation> selectFollow(@Param("userId") Integer userId, @Param("relation") RelationFlagEnum relation, @Param("date") Date date);

        @Select("select count(*) from relation where user_id1=#{userId} and follow_flag=#{relation}")
        int countFollow(@Param("userId") Integer userId, @Param("relation") RelationFlagEnum relation);

        @Select("select * from relation where user_id1=#{userId1} and user_id2=#{userId2}")
        Relation getByUserId1AndUserId2(@Param("userId1") Integer userId1, @Param("userId2") Integer userId2);
    }
}
