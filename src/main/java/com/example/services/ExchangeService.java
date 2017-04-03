package com.example.services;

import com.example.model.ConversionResult;
import com.example.model.Exchange;
import com.example.repository.ExchangeRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 * Created by woorea on 03/04/2017.
 */
public class ExchangeService {

  private ExchangeRepository repository;

  public ConversionResult convert(LocalDate now, BigDecimal fromAmount, String fromCurrency, String toCurrency) {

    ConversionResult result = new ConversionResult();

    if("EUR".equals(fromCurrency)) {

      Exchange exchange = repository.findByDateAndCurrency(now, toCurrency);

      result.setToAmount(fromAmount.multiply(exchange.getRate()));

    } else {

      Exchange exchange = repository.findByDateAndCurrency(now, fromCurrency);

      result.setToAmount(fromAmount.divide(exchange.getRate(), 5, RoundingMode.HALF_EVEN));

    }

    return result;

  }

}
