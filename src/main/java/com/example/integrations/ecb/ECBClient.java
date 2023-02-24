package com.example.integrations.ecb;

import com.example.model.Exchange;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by woorea on 03/04/2017.
 */
@Component
public class ECBClient {

  //these urls should be externalized, for simplicity I will maintain hardcoded

  public final String dailyRatesUrl = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";

  public final String history90daysUrl = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist-90d.xml";

  public final String historyUrl = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist.xml";


  private final RestTemplate restTemplate;

  public ECBClient(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  // since we don't have a xsd and the model XML is very simple a custom unmarshaller has been implemented
  // to avoid increment technology complexity (jaxb, castor for spring-oxm)
  private final ECBUnmarshaller unmarshaller = new ECBUnmarshaller();

  public List<Exchange> getHistoryRatesForLast90days() {

    return getRatesFromUrl(history90daysUrl);

  }

  public List<Exchange> getDailyRates() {

    return getRatesFromUrl(dailyRatesUrl);

  }

  public List<Exchange> getAllHistoryRates() {

    return getRatesFromUrl(historyUrl);

  }

  private List<Exchange> getRatesFromUrl(String url) {

    ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

    try {
      return unmarshaller.apply(responseEntity.getBody());
    } catch (Exception e) {
      return new ArrayList<>();
    }

  }

}
