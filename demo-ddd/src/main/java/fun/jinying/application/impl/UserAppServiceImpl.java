package fun.jinying.application.impl;

import fun.jinying.application.UserAppService;
import fun.jinying.domain.shard.model.EventProducer;
import fun.jinying.domain.user.model.User;
import fun.jinying.domain.user.model.UserEvent;
import fun.jinying.domain.user.model.UserService;
import fun.jinying.domain.user.model.UserUpdater;
import fun.jinying.domain.user.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @description:
 * @author: sjy
 * @create: 2020-02-28 18:08
 **/
@Component
public class UserAppServiceImpl implements UserAppService {
    private UserRepository userRepository;
    private EventProducer<UserEvent> userEventProducer;
    private UserService userService;

    public UserAppServiceImpl(UserRepository userRepository, EventProducer<UserEvent> userEventProducer, UserService userService) {
        this.userRepository = userRepository;
        this.userEventProducer = userEventProducer;
        this.userService = userService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User register(String phone) {
        Integer userId = userRepository.nextUserId();
        String userName = userService.getRandomUserName();
        String avatar = userService.getRandomAvatar();
        User user = User.createNewUser(userId, phone, userName, avatar);
        userRepository.saveUser(user);
        UserEvent userEvent = user.register();
        userEventProducer.sendEvent(userEvent);
        return user;
    }

    @Override
    public Optional<User> getRegisteredUser(String phone) {
        return userRepository.getByPhone(phone);
    }

    @Override
    public void login(User user) {
        userEventProducer.sendEvent(user.login());
    }

    @Override
    public Optional<User> getUser(String userId) {
        return userRepository.getByUserId(userId);
    }

    @Override
    public User update(String userId, UserUpdater userUpdater) {
        User user = userRepository.getByUserId(userId).orElseThrow(() -> new IllegalStateException(userId + "not exits"));
        userRepository.update(user.getUserId(), userUpdater);
        userEventProducer.sendEvent(user.updateUserName(userUpdater.getUserName()));
        return user;
    }

}
