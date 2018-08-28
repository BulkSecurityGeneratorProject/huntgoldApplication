package io.github.jhipster.application.task;

import io.github.jhipster.application.Exchange.OkExchange;
import io.github.jhipster.application.kafka.KafkaSender;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.okcoin.FuturesContract;
import org.knowm.xchange.okcoin.dto.marketdata.OkCoinDepth;
import org.knowm.xchange.okcoin.dto.marketdata.OkCoinTickerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by lujango on 2018/8/4.
 * 行情
 */
//@Component
public class TickerTask {

    private final Logger log = LoggerFactory.getLogger(TickerTask.class);
    private final static String TOPIC_TICKER = "topic_ticker";
    @Autowired
    KafkaSender sender;
    @Autowired
    OkExchange okExchange;

    @Scheduled(fixedRate = 10000)
    public void getFuturesDepth() {
        try {
            OkCoinTickerResponse okCoinTickerResponse = okExchange
                .getFutureService()
                .getFuturesTicker(CurrencyPair.DASH_BTC, FuturesContract.Quarter);

            sender.send(TOPIC_TICKER, okCoinTickerResponse.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
