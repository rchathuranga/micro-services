const express = require('express');
const eurekaHelper = require('./eureka-config/eureka-helper');
const CurrencyRateBean = require('./bean/currency-rate-bean');

const app = express();
// const APPLICATION_NAME = "currency-rate-nodejs-service";
const APPLICATION_NAME = "currency-rate-service";
const PORT = 8004;

app.listen(PORT, () => {
    console.log(APPLICATION_NAME + " is running on port " + PORT);
})

app.get('/cxs/convert/fromCurrency/:fromCurrency/toCurrency/:toCurrency/amount/:amount', (req, res) => {
    const params = req.params;
    let rateBean = new CurrencyRateBean(params.fromCurrency, params.toCurrency, params.amount, PORT);
    console.log(rateBean)
    res.send(rateBean);
    res.end();
})
eurekaHelper.registerWithEureka(APPLICATION_NAME, PORT);

