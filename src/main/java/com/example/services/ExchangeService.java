package com.example.services;

import com.example.integrations.ecb.ECBClient;
import com.example.model.ConversionResult;
import com.example.model.Exchange;
import com.example.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by woorea on 03/04/2017.
 */
@Service
public class ExchangeService {

  @Autowired
  private ECBClient client;

  @Autowired
  private ExchangeRepository repository;

  public ConversionResult convert(LocalDate date, BigDecimal fromAmount, String fromCurrency, String toCurrency) {

    ConversionResult result = new ConversionResult();
    result.setDate(date);
    result.setFromAmount(fromAmount);
    result.setFromCurrency(fromCurrency);

    if("EUR".equals(fromCurrency)) {

      Exchange exchange = repository.findByDateAndCurrency(date, toCurrency);

      result.setToAmount(fromAmount.multiply(exchange.getRate()));

    } else {

      Exchange exchange = repository.findByDateAndCurrency(date, fromCurrency);

      result.setToAmount(fromAmount.divide(exchange.getRate(), 5, RoundingMode.HALF_EVEN));

    }

    result.setToCurrency(toCurrency);

    return result;

  }

  public void init() {

    List<Exchange> exchanges = client.getHistoryRatesForLast90days();

    exchanges.stream().forEach(repository::add);

  }

  public void refresh() {

    List<Exchange> exchanges = client.getDailyRates();

    exchanges.stream().forEach(repository::add);

  }


}
