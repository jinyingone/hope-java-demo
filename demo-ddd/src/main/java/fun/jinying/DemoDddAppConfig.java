package fun.jinying;

import fun.jinying.domain.user.factory.UserFactory;
import fun.jinying.domain.user.model.UserConfig;
import fun.jinying.domain.user.repository.UserRepository;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 配置
 * @author: sjy
 * @create: 2020-02-29 15:35
 **/
@Configuration
public class DemoDddAppConfig {

    @Bean
    @ConfigurationProperties(prefix = "demo.ddd.user")
    public UserConfig userConfig() {
        return new UserConfig();
    }

    @Bean
    public UserFactory userFactory(UserRepository userRepository, UserConfig userConfig) {
        return new UserFactory(userRepository, userConfig);
    }
}
