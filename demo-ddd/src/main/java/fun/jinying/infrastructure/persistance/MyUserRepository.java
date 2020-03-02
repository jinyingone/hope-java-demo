package fun.jinying.infrastructure.persistance;

import fun.jinying.domain.user.model.User;
import fun.jinying.domain.user.model.UserUpdater;
import fun.jinying.domain.user.repository.UserRepository;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
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

    @Override
    public Optional<User> getByPhone(String phone) {
        return Optional.ofNullable(userMapper.getByPhone(phone));
    }

    @Override
    public Optional<User> getByUserId(String userId) {
        return Optional.ofNullable(userMapper.getByUserId(userId));
    }

    @Override
    public int update(Integer userId, UserUpdater userUpdater) {
        return userMapper.updateUser(userId, userUpdater);
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
        @Insert("insert into user(user_id,phone,user_name,avatar,password,create_time,update_time)" +
                "values(#{user.userId},#{user.phone},#{user.userName},#{user.avatar},#{user.password},#{user.createTime},#{user.updateTime})")
        int saveUser(@Param("user") User user);

        /**
         * 根据手机号查找
         *
         * @param phone 手机号
         * @return
         */
        @Select("select user_id,user_name,avatar,phone,create_time,update_time from user where phone=#{phone}")
        User getByPhone(@Param("phone") String phone);

        /**
         * 根据userId查找
         *
         * @param userId
         * @return
         */
        @Select("select user_id,user_name,avatar,phone,create_time,update_time from user where user_id=#{userId}")
        User getByUserId(@Param("userId") String userId);


        int updateUser(@Param("userId") Integer userId, @Param("userUpdater") UserUpdater userUpdater);
    }

}
