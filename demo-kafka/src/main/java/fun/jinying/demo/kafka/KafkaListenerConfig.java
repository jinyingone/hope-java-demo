package fun.jinying.demo.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * kafka 消费配置
 *
 * @author jy
 * @date 2019-05-24 上午11:19
 **/
@Configuration
public class KafkaListenerConfig {
    @Value("${hope.demo.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public KafkaListenerContainerFactory demoKafkaListenerFactory() {
        Map<String, Object> kafkaConfig = new HashMap<>();
        kafkaConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        kafkaConfig.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        kafkaConfig.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 30000);
        kafkaConfig.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 10);
        kafkaConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        kafkaConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        ConcurrentKafkaListenerContainerFactory containerFactory = new ConcurrentKafkaListenerContainerFactory();
        containerFactory.setConsumerFactory(new DefaultKafkaConsumerFactory(kafkaConfig));
        return containerFactory;
    }
}
