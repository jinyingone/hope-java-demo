package fun.jinying.domain.user.factory;

import fun.jinying.domain.user.model.*;
import fun.jinying.domain.user.repository.UserRepository;

import java.util.Date;

/**
 * @description: 用户工厂
 * @author: sjy
 * @create: 2020-02-28 18:16
 **/
public class UserFactory {
    private UserRepository userRepository;
    private UserConfig userConfig;

    public UserFactory(UserRepository userRepository, UserConfig userConfig) {
        this.userRepository = userRepository;
        this.userConfig = userConfig;
    }

    public User newUser(String userName, String avatar, String password) {
        Integer nextUserId = userRepository.nextUserId();
        User user = new User();
        user.setUserId(nextUserId);
        user.setUserName(userName);
        user.setAvatar(avatar);
        user.setPassword(password);
        return user;
    }

    public User newUser(String phone) {
        User user = new User();
        user.setPhone(phone);
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        user.setUserId(userRepository.nextUserId());
        user.setAvatar(userConfig.getDefaultAvatar());
        user.setDefaultUserName(userRepository.nextUserNameSequence().toString());
        user.setInitializePassword();
        return user;
    }

    public UserRegisteredEvent userRegisteredEvent(User user) {
        UserRegisteredEvent userRegisteredEvent = new UserRegisteredEvent();
        userRegisteredEvent.setUserId(user.getUserId().toString());
        userRegisteredEvent.setType(UserEventTypeEnum.REGISGERED);
        return userRegisteredEvent;
    }

    public UserEvent userLoggedEvent(User user) {
        UserLoggedEvent event = new UserLoggedEvent();
        event.setUserId(user.getUserId().toString());
        event.setType(UserEventTypeEnum.LOGED);
        return event;
    }

    public UserEvent userUpdatedEvent(User oldUser, UserUpdater userUpdater) {
        UserLoggedEvent event = new UserLoggedEvent();
        event.setUserId(oldUser.getUserId().toString());
        event.setUpdater(userUpdater);
        event.setOldUser(oldUser);
        event.setType(UserEventTypeEnum.UPDATED);
        return event;
    }
}
