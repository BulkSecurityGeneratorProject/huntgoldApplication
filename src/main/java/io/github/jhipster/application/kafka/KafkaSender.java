package io.github.jhipster.application.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by lujango on 2018/7/27.
 */

@Component
public class KafkaSender {

    private final Logger log = LoggerFactory.getLogger(KafkaSender.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //发送消息方法
    public void send(String topic, String message) {
        log.info("kafka message sender :"+message);
        kafkaTemplate.send(topic, message);
    }
}
