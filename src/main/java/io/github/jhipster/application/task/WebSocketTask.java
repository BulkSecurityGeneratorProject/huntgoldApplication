package io.github.jhipster.application.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import info.bitrich.xchangestream.core.StreamingExchange;
import info.bitrich.xchangestream.core.StreamingExchangeFactory;
import info.bitrich.xchangestream.okcoin.OkExStreamingExchange;
import io.github.jhipster.application.Exchange.OkExchange;
import io.github.jhipster.application.kafka.KafkaSender;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.okcoin.FuturesContract;
import org.knowm.xchange.okcoin.OkCoinExchange;
import org.knowm.xchange.okcoin.service.OkCoinFuturesMarketDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by lujango on 2018/7/27.
 */
@Component
public class WebSocketTask {
    private final Logger log = LoggerFactory.getLogger(WebSocketTask.class);
    private final static String TOPIC_OKEX = "topic_okex";
    private final static String TOPIC_KLINE = "topic_kline";
    private final static String TOPIC_FUTURES_TIKER = "topic_kline";
    private final static String TOPIC_FUTURES_TRADE = "topic_kline";

    @Autowired
    KafkaSender sender;

    public void startOKex() {
        log.info("startOKex task start!!!");

        StreamingExchange exchange = StreamingExchangeFactory.INSTANCE
            .createExchange(OkExStreamingExchange.class.getName());

        // Connect to the Exchange WebSocket API. Blocking wait for the connection.
        exchange.connect().blockingAwait();
        System.out.println("isalive :" + exchange.isAlive());

      /*  exchange.getStreamingMarketDataService()
            .getTrades(CurrencyPair.ETH_BTC)
            .subscribe(trade -> {
               // sender.send(TOPIC_OKEX, trade.toString());

                log.info(trade.toString());
            }, throwable -> {
                log.error("Error in subscribing trades." + throwable.toString());
            });*/

        exchange.getStreamingMarketDataService()
            .getTicker(CurrencyPair.BTC_USDT)
            .subscribe(ticker -> {
                // sender.send(TOPIC_OKEX, trade.toString());

                log.info(ticker.toString());
            }, throwable -> {
                log.error("Error in subscribing trades." + throwable.toString());
            });


       /* exchange.getStreamingMarketDataService()
            .getTrades(CurrencyPair.EOS_BTC)
            .subscribe(trade -> {
               // sender.send(TOPIC_OKEX, trade.toString());
                log.info( trade.toString());
            }, throwable -> {
                log.error("Error in subscribing trades." + throwable.toString());
            });*/
    }
}
