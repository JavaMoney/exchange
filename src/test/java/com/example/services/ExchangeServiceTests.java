package com.example.services;

import com.example.model.ConversionResult;
import com.example.model.Exchange;
import com.example.repository.ExchangeRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by woorea on 03/04/2017.
 */
public class ExchangeServiceTests {

  private ExchangeService service;

  @Before
  public void before() {

    Exchange exchange = new Exchange();
    exchange.setDate(LocalDate.now());
    exchange.setCurrency("GBP");
    exchange.setRate(new BigDecimal("0.75"));

    ExchangeRepository repository = Mockito.mock(ExchangeRepository.class);

    Mockito.when(repository.findByDateAndCurrency(LocalDate.now(), "GBP")).thenReturn(exchange);

    service = new ExchangeService();
    ReflectionTestUtils.setField(service, "repository", repository);

  }

  @Test
  public void itShouldConvertEurToGbp() {

    ConversionResult result = service.convert(LocalDate.now(), new BigDecimal("1.0"), "EUR", "GBP");

    assertThat(result.getToAmount()).isEqualByComparingTo(new BigDecimal("0.75"));

  }

  @Test
  public void itShouldConvertGbpToEur() {

    ConversionResult result = service.convert(LocalDate.now(), new BigDecimal("0.75"), "GBP", "EUR");

    assertThat(result.getToAmount()).isEqualByComparingTo(new BigDecimal("1.00"));

  }

}
