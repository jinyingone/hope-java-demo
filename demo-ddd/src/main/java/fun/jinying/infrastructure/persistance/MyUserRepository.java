package fun.jinying.infrastructure.persistance;

import fun.jinying.domain.user.model.User;
import fun.jinying.domain.user.repository.UserRepository;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: sjy
 * @create: 2020-02-28 18:35
 **/
@Component
public class MyUserRepository implements UserRepository {
    @Autowired
    private UserMapper userMapper;
    private static final AtomicInteger userIdSequence = new AtomicInteger(10000);
    private static final AtomicInteger userNameSequence = new AtomicInteger(10000);

    @Override
    public Integer nextUserId() {
        return userIdSequence.incrementAndGet();
    }

    @Override
    public Integer nextUserNameSequence() {
        return userNameSequence.incrementAndGet();
    }

    @Override
    public int saveUser(User user) {
        return userMapper.saveUser(user);
    }

    @Mapper
    @Component
    public interface UserMapper {
        /**
         * 保存用户
         *
         * @param user
         * @return
         */
        @Insert("insert into user(user_id,phone,user_name,avatar,create_time,update_time)" +
                "values(#{user.userId},#{user.phone},#{user.userName},#{user.avatar},#{user.createTime},#{user.updateTime})")
        int saveUser(@Param("user") User user);
    }

}
