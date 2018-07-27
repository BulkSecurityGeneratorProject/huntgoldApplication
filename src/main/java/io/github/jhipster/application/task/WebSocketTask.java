package io.github.jhipster.application.task;

import info.bitrich.xchangestream.core.StreamingExchange;
import info.bitrich.xchangestream.core.StreamingExchangeFactory;
import info.bitrich.xchangestream.okcoin.OkExStreamingExchange;
import io.github.jhipster.application.kafka.KafkaSender;
import org.knowm.xchange.currency.CurrencyPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

/**
 * Created by lujango on 2018/7/27.
 */
@Component
public class WebSocketTask {

    private final Logger log = LoggerFactory.getLogger(WebSocketTask.class);

    private final static String TOPIC_OKEX = "topic_okex";


    @Autowired
    KafkaSender sender;


    // @Scheduled(fixedRate = 5000)
    public void test() {
        for (int i = 0; i < 3; i++) {
            //调用消息发送类中的消息发送方法
            //       sender.send();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startOKex() {
        log.info("startOKex task start!!!");

        StreamingExchange exchange = StreamingExchangeFactory.INSTANCE
            .createExchange(OkExStreamingExchange.class.getName());

        // Connect to the Exchange WebSocket API. Blocking wait for the connection.
        exchange.connect().blockingAwait();

        System.out.println("isalive :" + exchange.isAlive());

        exchange.getStreamingMarketDataService()
            .getTrades(CurrencyPair.ETH_BTC)
            .subscribe(trade -> {
                sender.send(TOPIC_OKEX, trade.toString());
            }, throwable -> {
                log.error("Error in subscribing trades." + throwable.toString());
            });

        exchange.getStreamingMarketDataService()
            .getTrades(CurrencyPair.EOS_BTC)
            .subscribe(trade -> {
                sender.send(TOPIC_OKEX, trade.toString());
            }, throwable -> {
                log.error("Error in subscribing trades." + throwable.toString());
            });
    }
}
