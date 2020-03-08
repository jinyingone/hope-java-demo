package fun.jinying;

import fun.jinying.application.SmsService;
import fun.jinying.domain.feed.factory.FeedFactory;
import fun.jinying.domain.feed.repository.FeedRepository;
import fun.jinying.domain.relation.factory.RelationFactory;
import fun.jinying.domain.user.factory.UserFactory;
import fun.jinying.domain.user.model.UserConfig;
import fun.jinying.domain.user.repository.UserRepository;
import fun.jinying.infrastructure.OuterSmsService;
import fun.jinying.infrastructure.message.FeedEventProducer;
import fun.jinying.infrastructure.message.UserEventProducer;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
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

    @Bean
    public SmsService smsService() {
        return new OuterSmsService();
    }

    @Bean
    public TopicExchange userExchange() {
        TopicExchange topicExchange = new TopicExchange(UserEventProducer.EXCHANGE);
        return topicExchange;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public FeedFactory feedFactory(FeedRepository feedRepository) {
        return new FeedFactory(feedRepository);
    }

    @Bean
    public TopicExchange feedExchange(FeedEventProducer feedEventProducer) {
        TopicExchange topicExchange = new TopicExchange(feedEventProducer.getExchange());
        return topicExchange;
    }

    @Bean
    public RelationFactory relationFactory() {
        return new RelationFactory();
    }

}
