package fun.jinying.interfaces.feed.facade.internal.assembler;

import fun.jinying.domain.feed.model.RepostFeed;
import fun.jinying.domain.feed.model.Feed;
import fun.jinying.domain.feed.model.FeedActionTypeEnum;
import fun.jinying.interfaces.feed.facade.dto.FeedDTO;
import fun.jinying.interfaces.feed.facade.dto.RepostFeedDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-06 22:51
 **/
public class FeedDtoAssembler {
    public static FeedDTO toDTO(Feed feed) {
        FeedDTO feedDTO = new FeedDTO();
        feedDTO.setFeedId(feed.getFeedId().toString());
        feedDTO.setStatus(feed.getStatus().getValue());
        feedDTO.setText(feed.getText());
        feedDTO.setTime(feed.getTime().getTime());
        feedDTO.setUserId(feed.getUserId().toString());
        feedDTO.setActionType(feed.getActionType().getValue());
        feedDTO.setContentType(feed.getContentType().getValue());

        if (feed.getActionType() == FeedActionTypeEnum.REPOST && Objects.nonNull(feed.getRepostFeed())) {
            feedDTO.setRepost(toRepostFeedDTO(feed.getRepostFeed()));
        }
        return feedDTO;
    }

    private static RepostFeedDTO toRepostFeedDTO(RepostFeed repostFeed) {
        RepostFeedDTO repostFeedDTO = new RepostFeedDTO();
        repostFeedDTO.setFeedId(repostFeed.getFeedId().toString());
        List<RepostFeedDTO> feedLink = repostFeed.getRepostFeedLinkItems().stream()
                .map(item -> new RepostFeedDTO(item.getFeedId().toString(), item.getUserId().toString(), item.getText(), item.getStatusEnum().getValue())).collect(Collectors.toList());
        repostFeedDTO.setFeedLink(feedLink);
        return repostFeedDTO;
    }

    public static Long getScore(FeedDTO dto) {
        return -dto.getTime();
    }
}
