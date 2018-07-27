package io.github.jhipster.application.kafka;

import java.util.Optional;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by lujango on 2018/7/27.
 */
@Component
public class KafkaReceiver {

    private final Logger log = LoggerFactory.getLogger(KafkaReceiver.class);

    @KafkaListener(topics = {"topic_okex"})
    public void listen(ConsumerRecord<?, ?> record) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();

            // log.info("topic receiver :" + record);
            log.info("topic receiver :" + message);
        }

    }
}
