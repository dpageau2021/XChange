package org.knowm.xchange.paymium.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.client.ExchangeRestProxyBuilder;
import org.knowm.xchange.paymium.Paymium;
import org.knowm.xchange.paymium.PaymiumAuthenticated;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import si.mazi.rescu.ParamsDigest;

public class PaymiumBaseService extends BaseExchangeService implements BaseService {

  protected final Paymium paymium;

  protected final PaymiumAuthenticated paymiumAuthenticated;

  protected final String apiKey;

  protected final ParamsDigest signatureCreator;

  /**
   * Constructor
   *
   * @param exchange
   */
  protected PaymiumBaseService(Exchange exchange) {

    super(exchange);

    this.paymium =
        ExchangeRestProxyBuilder.forInterface(Paymium.class, exchange.getExchangeSpecification())
            .build();

    this.paymiumAuthenticated =
        ExchangeRestProxyBuilder.forInterface(
                PaymiumAuthenticated.class, exchange.getExchangeSpecification())
            .build();

    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    this.signatureCreator =
        PaymiumDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
  }
}
