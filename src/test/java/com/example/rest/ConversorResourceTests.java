package com.example.rest;

import com.example.model.ConversionResult;
import com.example.services.ExchangeService;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by woorea on 03/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConversorResourceTests {

  @Autowired
  private ConversorResource resource;

  @LocalServerPort
  private int port;

  @Before
  public void before() throws Exception {

    RestAssured.port = port;
    RestAssured.baseURI = "http://localhost";

    ConversionResult result = new ConversionResult();
    result.setDate(LocalDate.of(2017,1,1));
    result.setFromAmount(new BigDecimal("1.0"));
    result.setFromCurrency("EUR");
    result.setToAmount(new BigDecimal("1.25"));
    result.setToCurrency("GBP");

    ExchangeService service = Mockito.mock(ExchangeService.class);
    Mockito.when(service.convert(LocalDate.now(), new BigDecimal("1.0"), "EUR", "GBP")).thenReturn(result);
    ReflectionTestUtils.setField(resource, "exchangeService", service);

  }

  @Test
  public void itShouldGetConversion() {
    RestAssured
      .given()
        .queryParam("from_amount", "1.0")
        .queryParam("from_currency", "EUR")
        .queryParam("to_currency", "GBP")
      .when()
        .get("/convert")
      .then()
        .log().all()
        .statusCode(200)
        .body("date", Matchers.equalTo("2017-01-01"))
        .body("fromAmount", Matchers.equalTo(1.0f))
        .body("fromCurrency", Matchers.equalTo("EUR"))
        .body("toAmount", Matchers.equalTo(1.25f))
        .body("toCurrency", Matchers.equalTo("GBP"));
  }

}
