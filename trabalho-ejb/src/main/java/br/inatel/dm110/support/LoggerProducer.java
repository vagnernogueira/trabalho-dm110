package br.inatel.dm110.support;

import java.util.logging.Logger;

import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;

public class LoggerProducer {

  @Produces
  public Logger getLogger(InjectionPoint ip) {
    return Logger.getLogger(ip.getMember().getDeclaringClass().getName());
  }
}