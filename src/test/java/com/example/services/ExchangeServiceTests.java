package com.example.services;

import com.example.model.ConversionResult;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by woorea on 03/04/2017.
 */
public class ExchangeServiceTests {

  public void before() {

  }

  @Test
  public void itShouldConvertEurToGbp() {

    ExchangeService service = new ExchangeService();

    ConversionResult result = service.convert(LocalDate.now(), new BigDecimal("1.0"), "EUR", "GBP");

    assertThat(result.getToAmount()).isEqualByComparingTo(new BigDecimal("0.75"));

  }

  @Test
  public void itShouldConvertGbpToEur() {

    ExchangeService service = new ExchangeService();

    ConversionResult result = service.convert(LocalDate.now(), new BigDecimal("0.75"), "GBP", "EUR");

    assertThat(result.getToAmount()).isEqualByComparingTo(new BigDecimal("1.00"));

  }

}
