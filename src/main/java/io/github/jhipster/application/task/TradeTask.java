package io.github.jhipster.application.task;

import io.github.jhipster.application.Exchange.OkExchange;
import io.github.jhipster.application.kafka.KafkaSender;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.okcoin.FuturesContract;
import org.knowm.xchange.okcoin.dto.marketdata.OkCoinTickerResponse;
import org.knowm.xchange.okcoin.dto.marketdata.OkCoinTrade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by lujango on 2018/8/4.
 * 已交易
 */

public class TradeTask {


    private final Logger log = LoggerFactory.getLogger(TickerTask.class);
    private final static String TOPIC_TRADE = "topic_trade";
    @Autowired
    KafkaSender sender;
    @Autowired
    OkExchange okExchange;



    @Scheduled(fixedRate = 10000)
    public void getFuturesDepth() {
        try {
            FuturesContract[] futuresContracts = FuturesContract.values();
            //循环拉取行情
            for (FuturesContract futures : futuresContracts) {

                OkCoinTrade[] okCoinTrades = okExchange
                    .getFutureService()
                    .getFuturesTrades(CurrencyPair.DASH_BTC, futures);
                sender.send(TOPIC_TRADE, okCoinTrades.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
