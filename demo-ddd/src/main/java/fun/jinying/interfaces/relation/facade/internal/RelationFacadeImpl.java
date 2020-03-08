package fun.jinying.interfaces.relation.facade.internal;

import fun.jinying.application.RelationService;
import fun.jinying.domain.relation.model.Relation;
import fun.jinying.interfaces.relation.FollowCmd;
import fun.jinying.interfaces.relation.facade.RelationFacade;
import fun.jinying.interfaces.relation.facade.dto.RelationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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
        return new RelationDtoAssembler().toDTO(relation);
    }
}
