package fun.jinying.application.impl;

import fun.jinying.application.RelationService;
import fun.jinying.domain.relation.factory.RelationFactory;
import fun.jinying.domain.relation.model.Relation;
import fun.jinying.domain.relation.repository.RelationRepository;
import fun.jinying.interfaces.relation.FollowCmd;
import fun.jinying.interfaces.relation.ListFansCmd;
import fun.jinying.interfaces.relation.ListFollowCmd;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @description: 关系
 * @author: sjy
 * @create: 2020-03-08 10:22
 **/
@Component
public class RelationServiceImpl implements RelationService {
    private RelationRepository relationRepository;
    private RelationFactory relationFactory;

    public RelationServiceImpl(RelationRepository relationRepository, RelationFactory relationFactory) {
        this.relationRepository = relationRepository;
        this.relationFactory = relationFactory;
    }

    @Override
    @Transactional
    public Relation follow(FollowCmd followCmd) {
        Integer fans = Integer.valueOf(followCmd.getFansUserId());
        Integer follow = Integer.valueOf(followCmd.getFollowUserId());

        Relation fansRelation = relationFactory.newFansRelation(fans, follow);
        Relation followRelation = relationFactory.newFollowRelation(follow, fans);
        if (fans < follow) {
            relationRepository.saveFansRelation(fansRelation);
            relationRepository.saveFollowRelation(followRelation);
        } else {
            relationRepository.saveFollowRelation(followRelation);
            relationRepository.saveFansRelation(fansRelation);
        }

        return fansRelation;
    }

    @Override
    public List<Relation> listFans(ListFansCmd cmd) {
        return relationRepository.listFans(Integer.valueOf(cmd.getUserId()), new Date(cmd.getTime()));
    }

    @Override
    public int countFans(String userId) {
        return relationRepository.countFans(Integer.valueOf(userId));
    }

    @Override
    public List<Relation> listFollow(ListFollowCmd cmd) {
        return relationRepository.listFollow(Integer.valueOf(cmd.getUserId()), new Date(cmd.getTime()));
    }

    @Override
    public int countFollow(String userId) {
        return relationRepository.countFollow(Integer.valueOf(userId));
    }
}
