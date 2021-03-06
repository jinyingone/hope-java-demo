package fun.jinying.demo.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * 启动入口
 *
 * @author jy
 * @date 2019-05-24 上午10:48
 **/
@SpringBootApplication
@EnableKafka
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
