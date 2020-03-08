package fun.jinying.interfaces.relation;

import fun.jinying.interfaces.relation.facade.RelationFacade;
import fun.jinying.interfaces.relation.facade.dto.PageAndListDTO;
import fun.jinying.interfaces.relation.facade.dto.RelationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 用户关系
 * @author: sjy
 * @create: 2020-03-08 10:08
 **/
@RestController
@RequestMapping("/v1/demo/ddd/relation")
public class RelationController {
    @Autowired
    private RelationFacade relationFacade;

    @RequestMapping("/follow")
    public RelationDTO follow(@Validated @RequestBody FollowCmd followCmd,
                              @RequestHeader(name = "log-user-id") String logUserId) {
        followCmd.setFansUserId(logUserId);
        return relationFacade.follow(followCmd);
    }

    @RequestMapping("/list_fans")
    public PageAndListDTO listFans(@Validated @RequestBody ListFansCmd cmd,
                                   @RequestHeader(name = "log-user-id", required = false) String logUserId) {
        cmd.setLogUserId(logUserId);
        return relationFacade.listFans(cmd);
    }

    @RequestMapping("/list_follow")
    public PageAndListDTO listFans(@Validated @RequestBody ListFollowCmd cmd,
                                   @RequestHeader(name = "log-user-id", required = false) String logUserId) {
        cmd.setLogUserId(logUserId);
        return relationFacade.listFollow(cmd);
    }
}
