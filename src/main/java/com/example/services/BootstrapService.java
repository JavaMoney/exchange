package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by woorea on 03/04/2017.
 */
@Component
public class BootstrapService implements ApplicationRunner {

  @Autowired
  private ExchangeService exchangeService;

  @Override
  public void run(ApplicationArguments applicationArguments) throws Exception {
    exchangeService.init();
  }

}
