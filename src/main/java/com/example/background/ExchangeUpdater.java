package com.example.background;

import com.example.services.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by woorea on 03/04/2017.
 */
@Component
public class ExchangeUpdater {

  @Autowired
  private ExchangeService service;

  @Scheduled(cron = "05 16 * * * MON-FRI", zone = "CET")
  public void execute() {
    service.refresh();
  }

}
