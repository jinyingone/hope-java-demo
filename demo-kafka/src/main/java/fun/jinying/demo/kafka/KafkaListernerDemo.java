package fun.jinying.demo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * kafka消费 demo
 *
 * @author jy
 * @date 2019-05-24 上午10:46
 **/
@Service
public class KafkaListernerDemo {
    /**
     * 消费配置
     *
     * @param msg
     */
    @KafkaListener(groupId = "demo", topics = {"sns_hycs_anal_log_test"}, containerFactory = "demoKafkaListererFactory")
    public void listen(String msg) {
        System.out.println(msg);
    }
}
