package com.example.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by woorea on 03/04/2017.
 */
public class Exchange {

  private LocalDate date;

  private String currency;

  private BigDecimal rate;

  public Exchange() {
  }

  public Exchange(LocalDate date, String currency, BigDecimal rate) {
    this.date = date;
    this.currency = currency;
    this.rate = rate;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public void setRate(BigDecimal rate) {
    this.rate = rate;
  }
}
