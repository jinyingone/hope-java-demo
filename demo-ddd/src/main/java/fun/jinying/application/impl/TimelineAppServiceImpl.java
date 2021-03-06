package fun.jinying.application.impl;

import fun.jinying.application.TimelineAppService;
import fun.jinying.domain.feed.factory.FeedFactory;
import fun.jinying.domain.feed.model.*;
import fun.jinying.domain.feed.repository.FeedRepository;
import fun.jinying.domain.relation.model.Relation;
import fun.jinying.domain.relation.repository.RelationRepository;
import fun.jinying.domain.user.repository.UserRepository;
import fun.jinying.interfaces.feed.ListTimelineQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
public class TimelineAppServiceImpl implements TimelineAppService {
    private FeedRepository feedRepository;
    private UserRepository userRepository;
    private RelationRepository relationRepository;
    private FeedFactory feedFactory;

    public TimelineAppServiceImpl(FeedRepository feedRepository, UserRepository userRepository, RelationRepository relationRepository, FeedFactory feedFactory) {
        this.feedRepository = feedRepository;
        this.userRepository = userRepository;
        this.relationRepository = relationRepository;
        this.feedFactory = feedFactory;
    }

    @Override
    public List<Feed> listTimeline(ListTimelineQuery cmd) {
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
    @Transactional(rollbackFor = Exception.class)
    public void saveTimeLine(FeedEvent feedEvent) {
        feedRepository.saveTimeline(feedFactory.newTimelineItem(feedEvent.getFeed()));
        //todo 这个地方应该发送一个当前用户已经操作完成的事件
        List<Relation> relations = relationRepository.listFans(feedEvent.getFeed().getUserId(), feedEvent.getFeed().getCreateTime());
        relations.forEach(relation -> feedRepository.saveTimeline(feedFactory.newTimelineItem(relation.getUserId2(), feedEvent.getFeed())));
    }

    @Override
    public int countTimeline(ListTimelineQuery cmd) {
        Integer logUserId = Integer.valueOf(cmd.getLogUserId());
        return feedRepository.countTimelineItems(logUserId);
    }

}
