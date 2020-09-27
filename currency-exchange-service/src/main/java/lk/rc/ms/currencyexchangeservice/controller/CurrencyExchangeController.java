package lk.rc.ms.currencyexchangeservice.controller;

import lk.rc.ms.currencyexchangeservice.bean.CurrencyRateBean;
import lk.rc.ms.currencyexchangeservice.bean.ExchangeConvertBean;
import lk.rc.ms.currencyexchangeservice.client.RateServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/cxs")
public class CurrencyExchangeController {

    @Autowired
    private RateServiceClient rateServiceClient;

    @GetMapping(value = "/convert/fromCurrency/{fromCurrency}/toCurrency/{toCurrency}/amount/{amount}")
    public ResponseEntity<ExchangeConvertBean> getRate(@PathVariable String fromCurrency, @PathVariable String toCurrency, @PathVariable BigDecimal amount) {
        ExchangeConvertBean responseBean = new ExchangeConvertBean();
        try {
            CurrencyRateBean currencyRateBean = rateServiceClient.getRate(fromCurrency, toCurrency);
            responseBean.setFromCurrency(currencyRateBean.getFromCurrency());
            responseBean.setToCurrency(currencyRateBean.getToCurrency());
            responseBean.setAmount(amount);
            responseBean.setExchangeRate(currencyRateBean.getExchangeRate());
            responseBean.setConvertedAmount(amount.multiply(currencyRateBean.getExchangeRate()));
            responseBean.setServerPort(currencyRateBean.getServerPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }

}
