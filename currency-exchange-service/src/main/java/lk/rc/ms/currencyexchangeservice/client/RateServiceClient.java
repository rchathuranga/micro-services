package lk.rc.ms.currencyexchangeservice.client;

import lk.rc.ms.currencyexchangeservice.bean.CurrencyRateBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-rate-service", url = "http://localhost:8001/crs")
public interface RateServiceClient {

    @GetMapping(value = "/get-rate/fromCurrency/{fromCurrency}/toCurrency/{toCurrency}")
    public CurrencyRateBean getRate(@PathVariable String fromCurrency, @PathVariable String toCurrency) throws Exception;

}
