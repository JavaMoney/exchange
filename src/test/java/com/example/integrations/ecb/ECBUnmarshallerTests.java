package com.example.integrations.ecb;

import com.example.model.Exchange;
import com.example.utils.FileUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

/**
 * Created by woorea on 03/04/2017.
 */
public class ECBUnmarshallerTests {

  private ECBUnmarshaller unmarshaller = new ECBUnmarshaller();

  @Test
  public void itShouldUnmarshalDailyXml() throws Exception {

    String xml = FileUtils.readClassPathResourceAsString("eurofxref-daily.xml");

    List<Exchange> exchanges = unmarshaller.apply(xml);

    Assertions.assertThat(exchanges.size()).isEqualTo(31);

  }

  @Test
  public void itShouldUnmarshalHist90dXml() throws Exception{

    String xml = FileUtils.readClassPathResourceAsString("eurofxref-hist-90d.xml");

    List<Exchange> exchanges = unmarshaller.apply(xml);

    Assertions.assertThat(exchanges.size()).isEqualTo(2015);

  }

}
