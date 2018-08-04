package io.github.jhipster.application.Exchange;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.okcoin.FuturesContract;
import org.knowm.xchange.okcoin.OkCoinExchange;
import org.knowm.xchange.okcoin.service.OkCoinFuturesMarketDataService;
import org.knowm.xchange.okcoin.service.OkCoinMarketDataService;
import org.springframework.stereotype.Component;

/**
 * Created by lujango on 2018/8/3.
 */

@Component
public class OkExchange {

    Exchange bitstamp;

    public OkExchange() {
        bitstamp = ExchangeFactory.INSTANCE
            .createExchange(OkCoinExchange.class.getName());
    }

    public OkCoinFuturesMarketDataService getFutureService() {
        OkCoinFuturesMarketDataService okCoinFuturesMarketDataService = new OkCoinFuturesMarketDataService(
            bitstamp,
            FuturesContract.Quarter);
        return okCoinFuturesMarketDataService;
    }

    public OkCoinMarketDataService getMarketService() {
        OkCoinMarketDataService okCoinMarketDataService = new OkCoinMarketDataService(
            bitstamp);
        return okCoinMarketDataService;
    }

}
