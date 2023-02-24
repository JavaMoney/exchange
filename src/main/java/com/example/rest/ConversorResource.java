package com.example.rest;

import com.example.model.ConversionResult;
import com.example.services.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Created by woorea on 03/04/2017.
 */
@RestController
public class ConversorResource {

  @Autowired
  private ExchangeService exchangeService;

  @GetMapping(path = "/convert")
  public ResponseEntity<ConversionResult> converter(
    @RequestParam(value="date") Optional<String> date,
    @RequestParam(value="from_currency") String fromCurrency,
    @RequestParam(value="from_amount") BigDecimal fromAmount,
    @RequestParam(value="to_currency") String toCurrency
  ) {

    LocalDate localDate = date.map(it -> LocalDate.parse(it, DateTimeFormatter.ISO_LOCAL_DATE)).orElse(LocalDate.now());

    ConversionResult result = exchangeService.convert(localDate, fromAmount, fromCurrency, toCurrency);

    return ResponseEntity.ok(result);

  }

}
