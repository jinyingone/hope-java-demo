package fun.jinying.demo.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * kakak 生产着配置
 *
 * @author jy
 * @date 2019-05-24 下午12:05
 **/
@Configuration
public class KafkaSenderConfig {
    @Value("${hope.demo.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public KafkaTemplate kafkaTemplate() {
        Map<String, Object> config = new HashMap<>(8);
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        config.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 3000);
        config.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 3000);

        ProducerFactory producerFactory = new DefaultKafkaProducerFactory(config);
        return new KafkaTemplate(producerFactory);
    }
}
