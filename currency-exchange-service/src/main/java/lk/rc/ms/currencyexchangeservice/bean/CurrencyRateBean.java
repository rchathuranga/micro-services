package lk.rc.ms.currencyexchangeservice.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrencyRateBean {
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal exchangeRate;
    private int serverPort;
}
