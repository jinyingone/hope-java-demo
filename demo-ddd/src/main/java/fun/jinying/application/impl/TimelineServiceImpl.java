package fun.jinying.application.impl;

import fun.jinying.application.TimelineService;
import fun.jinying.domain.feed.factory.FeedFactory;
import fun.jinying.domain.feed.model.*;
import fun.jinying.domain.feed.repository.FeedRepository;
import fun.jinying.domain.relation.repository.RelationRepository;
import fun.jinying.domain.user.repository.UserRepository;
import fun.jinying.interfaces.feed.ListTimelineCmd;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description: timeline　应用层实现
 * @author: sjy
 * @create: 2020-03-12 11:53
 **/
@Component
public class TimelineServiceImpl implements TimelineService {
    private FeedRepository feedRepository;
    private UserRepository userRepository;
    private RelationRepository relationRepository;
    private FeedFactory feedFactory;

    public TimelineServiceImpl(FeedRepository feedRepository, UserRepository userRepository, RelationRepository relationRepository, FeedFactory feedFactory) {
        this.feedRepository = feedRepository;
        this.userRepository = userRepository;
        this.relationRepository = relationRepository;
        this.feedFactory = feedFactory;
    }

    @Override
    public List<Feed> listTimeline(ListTimelineCmd cmd) {
        Integer logUserId = Integer.valueOf(cmd.getLogUserId());
        List<TimelineItem> timelineItems = feedRepository.listTimeLine(logUserId, new Date(-cmd.getScore()), cmd.getCount());
        return timelineItems.stream()
                .map(item -> feedRepository.getFeed(item.getFeedId()).orElse(null))
                .filter(Objects::nonNull)
                .map(feed -> {
                    if (feed.getActionType() == FeedActionTypeEnum.REPOST) {
                        List<Feed> feeds = feedRepository.listFeeds(feed.getRepostFeed().getFeedLink().toArray(new String[0]));
                        List<RepostFeedLinkItem> items = feeds.stream().map(f -> new RepostFeedLinkItem(f.getFeedId(), f.getUserId(), f.getStatus(), f.getText())).collect(Collectors.toList());
                        feed.getRepostFeed().setRepostFeedLinkItems(items);
                    }
                    return feed;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void saveTimeLine(FeedEvent feedEvent) {
        feedRepository.saveTimeline(feedFactory.newTimelineItem(feedEvent.getFeed()));
    }

    @Override
    public int countTimeline(ListTimelineCmd cmd) {
        Integer logUserId = Integer.valueOf(cmd.getLogUserId());
        return feedRepository.countTimelineItems(logUserId);
    }

}
