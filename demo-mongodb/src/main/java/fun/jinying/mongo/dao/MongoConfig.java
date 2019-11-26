package fun.jinying.mongo.dao;

import com.mongodb.MongoClientOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: sjy
 * @create: 2019-11-25 18:09
 **/
@Configuration
public class MongoConfig {
    @Bean
    public MongoClientOptions mongoClientOptions() {
        return MongoClientOptions.builder()
                .minConnectionsPerHost(16)
                .connectionsPerHost(32)
                .maxWaitTime(60000)
                .connectTimeout(30 * 6000)
                .maxConnectionIdleTime(15 * 6000)
                .build();
    }
}
