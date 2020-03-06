package fun.jinying.interfaces.feed.facade.internal;

import fun.jinying.application.FeedService;
import fun.jinying.domain.feed.model.Feed;
import fun.jinying.interfaces.feed.PublishCmd;
import fun.jinying.interfaces.feed.facade.FeedFacade;
import fun.jinying.interfaces.feed.facade.dto.FeedDTO;
import fun.jinying.interfaces.feed.facade.internal.assembler.FeedDtoAssembler;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-06 22:26
 **/
@Component
public class FeedFacadeImpl implements FeedFacade {
    private FeedService feedService;

    public FeedFacadeImpl(FeedService feedService) {
        this.feedService = feedService;
    }

    @Override
    public FeedDTO publish(PublishCmd publishCmd) {
        Feed feed = feedService.publish(publishCmd);
        return new FeedDtoAssembler().toDTO(feed);
    }
}
