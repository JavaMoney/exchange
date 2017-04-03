package com.example.repository;

import com.example.model.Exchange;
import com.example.model.ExchangeNotFoundException;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by woorea on 03/04/2017.
 */
public class ExchangeRepository {

  private Set<Exchange> data = new HashSet<>();

  public Exchange findByDateAndCurrency(LocalDate date, String currency) {
    return data.stream()
      .filter(it -> it.getCurrency().equals(currency))
      .filter(it -> !it.getDate().isAfter(date))
      .max(Comparator.comparing(Exchange::getDate))
      .orElseThrow(() -> new ExchangeNotFoundException());
  }

  public void add(Exchange exchange) {
    data.add(exchange);
  }

  public long size() {
    return data.size();
  }

}
