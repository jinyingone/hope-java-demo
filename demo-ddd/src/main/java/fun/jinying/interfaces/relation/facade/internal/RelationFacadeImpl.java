package fun.jinying.interfaces.relation.facade.internal;

import fun.jinying.application.RelationAppService;
import fun.jinying.domain.relation.model.Relation;
import fun.jinying.interfaces.common.PageAndList;
import fun.jinying.interfaces.relation.FollowCmd;
import fun.jinying.interfaces.relation.ListFansCmd;
import fun.jinying.interfaces.relation.ListFollowCmd;
import fun.jinying.interfaces.relation.facade.RelationFacade;
import fun.jinying.interfaces.relation.facade.dto.RelationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @description:
 * @author: sjy
 * @create: 2020-03-08 10:18
 **/
@Component
public class RelationFacadeImpl implements RelationFacade {
    @Autowired
    private RelationAppService relationAppService;

    @Override
    public RelationDTO follow(FollowCmd followCmd) {
        Relation relation = relationAppService.follow(followCmd);
        return RelationDtoAssembler.toDTO(relation);
    }

    @Override
    public PageAndList listFans(ListFansCmd cmd) {
        cmd.setTime(cmd.getScore() == 0 ? System.currentTimeMillis() : -cmd.getScore());
        List<Relation> relationList = relationAppService.listFans(cmd);
        int totalCount = relationAppService.countFans(cmd.getUserId());
        PageAndList<Relation, RelationDTO> pageAndList = new PageAndList<>();
        pageAndList.init(totalCount, relationList, RelationDtoAssembler::toDTO, RelationDTO::getScore, 0);
        return pageAndList;
    }

    @Override
    public PageAndList listFollow(ListFollowCmd cmd) {
        cmd.setTime(cmd.getScore() == 0 ? System.currentTimeMillis() : -cmd.getScore());
        List<Relation> relationList = relationAppService.listFollow(cmd);
        int totalCount = relationAppService.countFollow(cmd.getUserId());
        PageAndList<Relation, RelationDTO> pageAndList = new PageAndList<>();
        pageAndList.init(totalCount, relationList, RelationDtoAssembler::toDTO, RelationDTO::getScore, 0);
        return pageAndList;
    }

    @Override
    public RelationDTO getRelation(Integer userId1, Integer userId2) {
        Relation relation = relationAppService.getRelation(userId1, userId2);
        return RelationDtoAssembler.toDTO(relation);
    }
}
