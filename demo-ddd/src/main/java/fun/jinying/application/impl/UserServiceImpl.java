package fun.jinying.application.impl;

import fun.jinying.application.UserService;
import fun.jinying.domain.user.factory.UserFactory;
import fun.jinying.domain.user.model.User;
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
public class UserServiceImpl implements UserService {
    private UserFactory userFactory;
    private UserRepository userRepository;

    public UserServiceImpl(UserFactory userFactory, UserRepository userRepository) {
        this.userFactory = userFactory;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User register(String phone) {
        userRepository.getByPhone(phone).ifPresent(u -> {
            throw new RuntimeException("");
        });
        User user = userFactory.newUser(phone);
        userRepository.saveUser(user);
        return user;
    }

    @Override
    public Optional<User> getRegisteredUser(String phone) {
        return userRepository.getByPhone(phone);
    }
}
