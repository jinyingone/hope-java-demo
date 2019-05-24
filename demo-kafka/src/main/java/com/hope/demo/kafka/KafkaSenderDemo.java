package com.hope.demo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * kafka发送消息 demo
 *
 * @author jy
 * @date 2019-05-24 上午10:49
 **/
@Service
public class KafkaSenderDemo {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 发送kafka消息
     *
     * @param msg
     */
    public void sendMsg(String topic, String msg) {
        kafkaTemplate.send(topic, msg);
    }
}
