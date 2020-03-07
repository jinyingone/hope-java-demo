package fun.jinying.interfaces.feed;

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
}