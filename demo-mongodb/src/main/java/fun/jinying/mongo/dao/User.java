package fun.jinying.mongo.dao;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @description:
 * @author: sjy
 * @create: 2019-11-25 18:07
 **/
@Data
public class User {
    @Id
    private String id;
    private String userId;
    private String userName;
    private int gender;
}
