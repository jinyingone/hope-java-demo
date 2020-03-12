package fun.jinying.interfaces.feed.facade.internal;

import fun.jinying.application.FeedService;
import fun.jinying.application.TimelineService;
import fun.jinying.domain.feed.model.Feed;
import fun.jinying.interfaces.common.PageAndList;
import fun.jinying.interfaces.feed.ListTimelineCmd;
import fun.jinying.interfaces.feed.PublishCmd;
import fun.jinying.interfaces.feed.RepostCmd;
import fun.jinying.interfaces.feed.facade.FeedFacade;
import fun.jinying.interfaces.feed.facade.dto.FeedDTO;
import fun.jinying.interfaces.feed.facade.internal.assembler.FeedDtoAssembler;
import fun.jinying.interfaces.relation.facade.RelationFacade;
import fun.jinying.interfaces.user.facade.UserServiceFacade;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-06 22:26
 **/
@Component
public class FeedFacadeImpl implements FeedFacade {
    private FeedService feedService;
    private TimelineService timelineService;
    private UserServiceFacade userServiceFacade;
    private RelationFacade relationFacade;

    public FeedFacadeImpl(FeedService feedService, TimelineService timelineService, UserServiceFacade userServiceFacade, RelationFacade relationFacade) {
        this.feedService = feedService;
        this.timelineService = timelineService;
        this.userServiceFacade = userServiceFacade;
        this.relationFacade = relationFacade;
    }

    @Override
    public FeedDTO publish(PublishCmd publishCmd) {
        Feed feed = feedService.publish(publishCmd);
        return FeedDtoAssembler.toDTO(feed);
    }

    @Override
    public FeedDTO repost(RepostCmd repostCmd) {
        Feed feed = feedService.repost(repostCmd);
        return FeedDtoAssembler.toDTO(feed);
    }

    @Override
    public PageAndList listTimeline(ListTimelineCmd cmd) {
        List<Feed> list = timelineService.listTimeline(cmd);
        int newCount = timelineService.countTimeline(cmd);
        PageAndList<Feed, FeedDTO> pageAndList = new PageAndList<>();
        pageAndList.init(newCount, list, FeedDtoAssembler::toDTO, FeedDtoAssembler::getScore, 0);
        pageAndList.getList().forEach(feedDTO -> feedDTO.andUserDTO(userServiceFacade::getUser).andRelationDTO(cmd.getLogUserId(), relationFacade::getRelation));
        return pageAndList;
    }


}
