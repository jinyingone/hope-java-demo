package fun.jinying.domain.relation.model;

import fun.jinying.domain.shard.model.Entity;
import lombok.Data;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @description: 关系
 * @author: sjy
 * @create: 2020-02-26 22:01
 **/
@Data
public class Relation implements Entity {
    private Long id;
    /**
     * 粉丝
     */
    private Integer userId1;
    /**
     * 关注
     */
    private Integer userId2;
    /**
     * 粉丝标记
     */
    private RelationFlagEnum fansFlag;
    /**
     * 关注标记
     */
    private RelationFlagEnum followFlag;
    /**
     * 关注时间
     */
    private Date followTime;
    /**
     * 粉丝时间
     */
    private Date fansTime;
    private Date createTime;
    private Date updateTime;

    /**
     * 获取用户关系
     *
     * @param flag
     * @return
     */
    public RelationFlagEnum getRelationFlag(RelationFlagEnum flag) {
        if (this.getFansFlag() == RelationFlagEnum.FANS && RelationFlagEnum.FOLLOW == this.getFollowFlag()) {
            return RelationFlagEnum.BOTH_FOLLOW;
        }
        if (flag == RelationFlagEnum.FANS) {
            return this.getFansFlag();
        }
        if (flag == RelationFlagEnum.FOLLOW) {
            return this.getFollowFlag();
        }
        return RelationFlagEnum.NONE;
    }

    /**
     * 关注
     *
     * @param relation
     * @return
     */
    public Relation follow(Relation relation) {
        return relation;
    }

    /**
     * 取关
     *
     * @param relation
     * @return
     */
    public Relation unFollow(Relation relation) {
        return relation;
    }

    /**
     * 关注列表
     *
     * @param userId
     * @return
     */
    public List<Relation> listFollows(String userId) {
        return Collections.emptyList();
    }

    /**
     * 粉丝列表
     *
     * @param userId
     * @return
     */
    public List<Relation> listFans(String userId) {
        return Collections.emptyList();
    }
}
