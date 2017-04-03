package com.example.services;

import com.example.model.ConversionResult;
import com.example.model.Exchange;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 * Created by woorea on 03/04/2017.
 */
public class ExchangeService {

  public ConversionResult convert(LocalDate now, BigDecimal fromAmount, String fromCurrency, String toCurrency) {

    Exchange exchange = new Exchange();
    exchange.setDate(LocalDate.now());
    exchange.setCurrency("GBP");
    exchange.setRate(new BigDecimal("0.75"));

    ConversionResult result = new ConversionResult();

    if("EUR".equals(fromCurrency)) {

      result.setToAmount(fromAmount.multiply(exchange.getRate()));

    } else {

      result.setToAmount(fromAmount.divide(exchange.getRate(), 5, RoundingMode.HALF_EVEN));

    }

    return result;

  }

}
