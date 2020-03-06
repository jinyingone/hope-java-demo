package fun.jinying.interfaces.feed.facade.internal.assembler;

import fun.jinying.domain.feed.model.Feed;
import fun.jinying.interfaces.feed.facade.dto.FeedDTO;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-06 22:51
 **/
public class FeedDtoAssembler {
    public FeedDTO toDTO(Feed feed) {
        FeedDTO feedDTO = new FeedDTO();
        feedDTO.setFeedId(feed.getFeedId().toString());
        feedDTO.setText(feed.getText());
        feedDTO.setTime(feed.getTime().getTime());
        feedDTO.setUserId(feed.getUserId().toString());
        return feedDTO;
    }
}
