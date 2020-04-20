package fun.jinying.interfaces.feed;

import fun.jinying.interfaces.common.PageAndList;
import fun.jinying.interfaces.feed.facade.FeedFacade;
import fun.jinying.interfaces.feed.facade.dto.FeedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-06 21:45
 **/
@RestController
@RequestMapping("/v1/demo/ddd/feed")
public class FeedController {
    @Autowired
    private FeedFacade feedFacade;

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public FeedDTO publish(@Validated @RequestBody PublishCmd publishCmd
            , @RequestHeader("log-user-id") String logUserId) {
        publishCmd.setLogUserId(Integer.valueOf(logUserId));
        return feedFacade.publish(publishCmd);
    }

    @RequestMapping(value = "/repost", method = RequestMethod.POST)
    public FeedDTO repost(@Validated @RequestBody RepostCmd repostCmd
            , @RequestHeader("log-user-id") String logUserId) {
        repostCmd.setLogUserId(Integer.valueOf(logUserId));
        return feedFacade.repost(repostCmd);
    }

    @RequestMapping(value = "/list_timeline", method = RequestMethod.POST)
    public PageAndList listTimeline(@Validated @RequestBody ListTimelineQuery cmd
            , @RequestHeader("log-user-id") String logUserId) {
        cmd.setLogUserId(logUserId);
        return feedFacade.listTimeline(cmd);
    }
}
