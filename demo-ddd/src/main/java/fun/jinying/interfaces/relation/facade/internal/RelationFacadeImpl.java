package fun.jinying.interfaces.relation.facade.internal;

import fun.jinying.application.RelationService;
import fun.jinying.domain.relation.model.Relation;
import fun.jinying.interfaces.relation.FollowCmd;
import fun.jinying.interfaces.relation.ListFansCmd;
import fun.jinying.interfaces.relation.facade.RelationFacade;
import fun.jinying.interfaces.relation.facade.dto.PageAndListDTO;
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
    private RelationService relationService;

    @Override
    public RelationDTO follow(FollowCmd followCmd) {
        Relation relation = relationService.follow(followCmd);
        return RelationDtoAssembler.toDTO(relation);
    }

    @Override
    public PageAndListDTO listFans(ListFansCmd cmd) {
        cmd.setTime(cmd.getScore() == 0 ? System.currentTimeMillis() : -cmd.getScore());
        List<Relation> relationList = relationService.listFans(cmd);
        int totalCount = relationService.countFans(cmd.getUserId());
        PageAndListDTO pageAndListDTO = new PageAndListDTO();
        pageAndListDTO.init(relationList, totalCount);
        return pageAndListDTO;
    }
}
