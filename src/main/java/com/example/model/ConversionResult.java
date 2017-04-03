package com.example.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by woorea on 03/04/2017.
 */
public class ConversionResult {

  private LocalDate date;

  private BigDecimal fromAmount;

  private String fromCurrency;

  private BigDecimal toAmount;

  private String toCurrency;

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public BigDecimal getFromAmount() {
    return fromAmount;
  }

  public void setFromAmount(BigDecimal fromAmount) {
    this.fromAmount = fromAmount;
  }

  public String getFromCurrency() {
    return fromCurrency;
  }

  public void setFromCurrency(String fromCurrency) {
    this.fromCurrency = fromCurrency;
  }

  public BigDecimal getToAmount() {
    return toAmount;
  }

  public void setToAmount(BigDecimal toAmount) {
    this.toAmount = toAmount;
  }

  public String getToCurrency() {
    return toCurrency;
  }

  public void setToCurrency(String toCurrency) {
    this.toCurrency = toCurrency;
  }
}
