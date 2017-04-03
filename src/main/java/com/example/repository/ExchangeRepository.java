package com.example.repository;

import com.example.model.Exchange;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by woorea on 03/04/2017.
 */
public class ExchangeRepository {

  private Set<Exchange> data = new HashSet<>();

  public Exchange findByDateAndCurrency(LocalDate now, String gbp) {
    return null;
  }

  public void add(Exchange exchange) {
    data.add(exchange);
  }

  public long size() {
    return data.size();
  }

}
