class CurrencyRateBean {

    constructor(
        fromCurrency,
        toCurrency,
        exchangeRate,
        serverPort
    ) {

        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.exchangeRate = exchangeRate;
        this.serverPort = serverPort;

    }
}

module.exports = CurrencyRateBean