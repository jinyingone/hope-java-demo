package fun.jinying.domain.user.model;

import fun.jinying.domain.shard.model.Vo;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @description: 关系
 * @author: sjy
 * @create: 2020-02-26 22:01
 **/
public class Relation implements Vo {
    private String userId;
    private String followUserId;
    private Date time;

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
