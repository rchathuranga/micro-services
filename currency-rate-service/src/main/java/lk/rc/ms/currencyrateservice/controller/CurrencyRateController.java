package lk.rc.ms.currencyrateservice.controller;

import lk.rc.ms.currencyrateservice.bean.CurrencyRateBean;
import lk.rc.ms.currencyrateservice.config.ExchangeRateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/crs")
public class CurrencyRateController {

    @Autowired
    private ExchangeRateConfig exchangeRateConfig;

    @GetMapping(value = "/get-rate/fromCurrency/{fromCurrency}/toCurrency/{toCurrency}")
    public ResponseEntity<CurrencyRateBean> getRate(@PathVariable String fromCurrency, @PathVariable String toCurrency) {
        CurrencyRateBean responseBean = new CurrencyRateBean();

        responseBean.setFromCurrency(fromCurrency);
        responseBean.setToCurrency(toCurrency);
        responseBean.setExchangeRate(BigDecimal.valueOf(1.23));
        responseBean.setServerPort(exchangeRateConfig.getServerPort());

        return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }

}
