package com.example.repositories;

import com.example.model.Exchange;
import com.example.repository.ExchangeRepository;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

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

}
