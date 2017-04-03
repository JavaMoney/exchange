package com.example.repositories;

import com.example.model.Exchange;
import com.example.model.ExchangeNotFoundException;
import com.example.repository.ExchangeRepository;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by woorea on 03/04/2017.
 */
public class ExchangeRepositoryTests {

  @Test
  public void itShouldAddExchange() {

    Exchange exchange = new Exchange();
    exchange.setDate(LocalDate.now());
    exchange.setCurrency("GBP");
    exchange.setRate(new BigDecimal("0.75"));

    ExchangeRepository repository = new ExchangeRepository();

    assertThat(repository.size()).isEqualTo(0L);

    repository.add(exchange);

    assertThat(repository.size()).isEqualTo(1L);

  }

  @Test
  public void itShouldFindTheLastKnownExchage() {

    //for weekends there is no exchange, so we get from friday
    //for a date is possible not updated yet, updates occurs at 16:00 CET, so we get the rate from the day before

    ExchangeRepository repository = new ExchangeRepository();

    Exchange yesterday = new Exchange();
    yesterday.setDate(LocalDate.now().minus(1, ChronoUnit.DAYS));
    yesterday.setCurrency("GBP");
    yesterday.setRate(new BigDecimal("0.75"));

    repository.add(yesterday);

    Exchange exchange = repository.findByDateAndCurrency(LocalDate.now(), "GBP");

    assertThat(exchange).isNotNull();
    assertThat(exchange.getRate()).isEqualByComparingTo(new BigDecimal("0.75"));

  }

  @Test(expected = ExchangeNotFoundException.class)
  public void itShouldThrowExceptionWhenExchangeNotFound() {

    ExchangeRepository repository = new ExchangeRepository();

    Exchange exchange = new Exchange();
    exchange.setDate(LocalDate.now());
    exchange.setCurrency("GBP");
    exchange.setRate(new BigDecimal("0.75"));

    repository.add(exchange);

    repository.findByDateAndCurrency(LocalDate.now().minus(1, ChronoUnit.YEARS), "GBP");

  }

  @Test
  public void itShouldUpdateExchange() {

    ExchangeRepository repository = new ExchangeRepository();

    assertThat(repository.size()).isEqualTo(0L);

    Exchange exchange = new Exchange();
    exchange.setDate(LocalDate.now());
    exchange.setCurrency("GBP");
    exchange.setRate(new BigDecimal("0.75"));

    repository.add(exchange);

    exchange = repository.findByDateAndCurrency(LocalDate.now(), "GBP");

    assertThat(exchange.getRate()).isEqualByComparingTo(new BigDecimal(0.75));

    exchange = new Exchange();
    exchange.setDate(LocalDate.now());
    exchange.setCurrency("GBP");
    exchange.setRate(new BigDecimal("1.00"));

    repository.add(exchange);

    assertThat(repository.size()).isEqualTo(1L);

    exchange = repository.findByDateAndCurrency(LocalDate.now(), "GBP");

    assertThat(exchange.getRate()).isEqualByComparingTo(new BigDecimal(1.00));

  }

}
