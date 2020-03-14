package fun.jinying.application.impl;

import fun.jinying.application.UserAppService;
import fun.jinying.domain.shard.model.EventProducer;
import fun.jinying.domain.user.factory.UserFactory;
import fun.jinying.domain.user.model.User;
import fun.jinying.domain.user.model.UserEvent;
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
    private UserFactory userFactory;
    private UserRepository userRepository;
    private EventProducer<UserEvent> userEventProducer;

    public UserAppServiceImpl(UserFactory userFactory, UserRepository userRepository, EventProducer<UserEvent> userEventProducer) {
        this.userFactory = userFactory;
        this.userRepository = userRepository;
        this.userEventProducer = userEventProducer;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User register(String phone) {
        User user = userFactory.newUser(phone);
        userRepository.saveUser(user);
        userEventProducer.sendEvent(userFactory.userRegisteredEvent(user));
        return user;
    }

    @Override
    public Optional<User> getRegisteredUser(String phone) {
        return userRepository.getByPhone(phone);
    }

    @Override
    public void login(User user) {
        userEventProducer.sendEvent(userFactory.userLoggedEvent(user));
    }

    @Override
    public Optional<User> getUser(String userId) {
        return userRepository.getByUserId(userId);
    }

    @Override
    public User update(User user, UserUpdater userUpdater) {
        userRepository.update(user.getUserId(), userUpdater);
        userEventProducer.sendEvent(userFactory.userUpdatedEvent(user, userUpdater));
        return user;
    }
}
