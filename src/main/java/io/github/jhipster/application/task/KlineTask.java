package io.github.jhipster.application.task;

import com.google.gson.Gson;
import io.github.jhipster.application.Exchange.OkExchange;
import io.github.jhipster.application.kafka.KafkaSender;
import java.util.List;
import javax.annotation.PostConstruct;
import org.knowm.xchange.currency.CurrencyPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by lujango on 2018/8/4.
 * k线拉取
 */
@Component
public class KlineTask {

    private final Logger log = LoggerFactory.getLogger(KlineTask.class);
    private final static String TOPIC_KLINE = "topic_kline";
    private final static CurrencyPair[] needCurrencyPairs = {CurrencyPair.DASH_BTC};

    @Autowired
    KafkaSender sender;

    @Autowired
    OkExchange okExchange;

    @PostConstruct
    @Scheduled(fixedRate = 30000)
    public void get1Klines() {
        log.info("getKlines 1min ");
        try {

            List<Object[]> resultList = okExchange.getFutureService()
                .getKlines(CurrencyPair.BTC_USDT, "1min");
           // sender.send(TOPIC_KLINE, "1min" + new Gson().toJson(resultList.get(0)));
            log.info("1min"+new Gson().toJson(resultList.get(0)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //
    @Scheduled(cron = "0 0/2 * * * *")
    public void get5Klines() {
        log.info("getKlines 5min ");
        try {
            List<Object[]> resultList = okExchange.getFutureService()
                .getKlines(CurrencyPair.BTC_USDT, "5min");
            sender.send(TOPIC_KLINE, "5min" + new Gson().toJson(resultList.get(0)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 0/5 * * * *")
    public void get15Klines() {
        log.info("getKlines 15min ");
        try {
            List<Object[]> resultList = okExchange.getFutureService()
                .getKlines(CurrencyPair.BTC_USDT, "15min");
            sender.send(TOPIC_KLINE, "15min" + new Gson().toJson(resultList.get(0)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 0/30 * * * *")
    public void get1hourKlines() {
        log.info("getKlines 1hour ");
        try {
            List<Object[]> resultList = okExchange.getFutureService()
                .getKlines(CurrencyPair.BTC_USDT, "1hour");
            sender.send(TOPIC_KLINE, "1hour" + new Gson().toJson(resultList.get(0)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
