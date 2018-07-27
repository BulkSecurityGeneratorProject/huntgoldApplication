package io.github.jhipster.application.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by lujango on 2018/7/27.
 */

@Component
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //发送消息方法
    public void send(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
