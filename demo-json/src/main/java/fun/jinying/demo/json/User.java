package fun.jinying.demo.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * user
 *
 * @author jy
 * @date 2019-08-29 下午5:15
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String userName;
    private String avatar;
    private Integer age;
    private Boolean login;
    private Date createTime;
    private Date updateTime;
}
