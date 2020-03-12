package fun.jinying.interfaces.feed.facade.dto;

import fun.jinying.interfaces.relation.facade.dto.RelationDTO;
import fun.jinying.interfaces.user.facade.dto.UserDTO;
import lombok.Data;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @description:
 * @author: sjy
 * @create: 2020-03-06 22:25
 **/
@Data
public class FeedDTO {
    public String feedId;
    private String userId;
    private int status;
    public String text;
    public Long time;
    public int actionType;
    public int contentType;
    private RepostFeedDTO repost;
    private UserDTO user;
    private RelationDTO relation;

    public FeedDTO andUserDTO(Function<String/*userId*/, UserDTO> userDTOFunction) {
        this.user = userDTOFunction.apply(this.getUserId());
        return this;
    }

    public FeedDTO andRelationDTO(String userId, BiFunction<Integer/*userId1*/, Integer/*userId2*/, RelationDTO> relationDTOFunction) {
        this.relation = relationDTOFunction.apply(Integer.valueOf(userId), Integer.valueOf(this.getUserId()));
        return this;
    }
}
