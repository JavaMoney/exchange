package com.example.integrations.ecb;

import com.example.model.Exchange;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import java.util.List;

/**
 * Created by woorea on 03/04/2017.
 */
@RunWith(SpringRunner.class)
@RestClientTest(ECBClient.class)
public class ECBClientTests {

  @Autowired
  private ECBClient client;

  @Autowired
  private MockRestServiceServer server;

  @Before
  public void before() {

  }

  @Test
  public void itShouldBeAbleToDownloadDailyRates()  {

    this.server.expect(
      MockRestRequestMatchers.requestTo(client.dailyRatesUrl)
    ).andRespond(
      MockRestResponseCreators.withSuccess(new ClassPathResource("eurofxref-daily.xml"), MediaType.APPLICATION_XML)
    );

    List<Exchange> exchanges = client.getDailyRates();

    Assertions.assertThat(exchanges.size()).isEqualTo(31);

  }

  @Test
  public void itShouldBeAbleToDownloadHistory90daysRates() {

    this.server.expect(
      MockRestRequestMatchers.requestTo(client.history90daysUrl)
    ).andRespond(
      MockRestResponseCreators.withSuccess(new ClassPathResource("eurofxref-hist-90d.xml"), MediaType.APPLICATION_XML)
    );

    List<Exchange> exchanges = client.getHistoryRatesForLast90days();

    Assertions.assertThat(exchanges.size()).isEqualTo(2015);

  }


}