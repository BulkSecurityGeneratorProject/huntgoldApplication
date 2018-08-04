package io.github.jhipster.application.task;

import io.github.jhipster.application.Exchange.OkExchange;
import io.github.jhipster.application.kafka.KafkaSender;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.okcoin.FuturesContract;
import org.knowm.xchange.okcoin.dto.marketdata.OkCoinDepth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by lujango on 2018/8/4.
 * 交易深度获取
 */
@Component
public class DepthTask {

    private final Logger log = LoggerFactory.getLogger(DepthTask.class);
    private final static String TOPIC_KLINE = "topic_depth";
    @Autowired
    KafkaSender sender;
    @Autowired
    OkExchange okExchange;

    @Scheduled(fixedRate = 10000)
    public void getFuturesDepth() {
        try {
            OkCoinDepth okCoinDepth = okExchange
                .getFutureService()
                .getFuturesDepth(CurrencyPair.DASH_BTC, FuturesContract.Quarter);

            log.info("okCoinDepth" + okCoinDepth.toString());
            sender.send(TOPIC_KLINE, okCoinDepth.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
