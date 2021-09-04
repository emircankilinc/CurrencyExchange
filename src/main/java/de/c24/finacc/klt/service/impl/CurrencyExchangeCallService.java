package de.c24.finacc.klt.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import de.c24.finacc.klt.cache.CurrencyCache;
import de.c24.finacc.klt.model.CurrencyModel;
import de.c24.finacc.klt.model.Error;
import de.c24.finacc.klt.model.ExchangeRates;
import de.c24.finacc.klt.service.intf.ICurrencyExchangeCallService;
import de.c24.finacc.klt.service.request.RequestCurrencyExchange;
import de.c24.finacc.klt.service.response.ResponseCurrencyExchange;

@Component
public class CurrencyExchangeCallService implements ICurrencyExchangeCallService {

	// rest api access key
	@Value("${exchange.api.access_key}")
	private String ACCESS_KEY;

	// rest api base url
	@Value("${exchange.api.url}")
	private String API_BASE_URL;

	@Autowired
	private CurrencyCache cache;

	@Override
	public ResponseCurrencyExchange getConvertedValue(RequestCurrencyExchange request) {

		ResponseCurrencyExchange responseCurrencyExchange = new ResponseCurrencyExchange();

		// Concat source and target currency information for cache mechanism
		StringBuilder rateKey = new StringBuilder().append(request.getSource().getCode())
				.append(request.getTarget().getCode());

		// Get cached value if it is exist
		BigDecimal cachedRate = cache.getCachedRate(rateKey.toString());

		// If currency already in cache there is no need to call rest api
		if (null != cachedRate) {
			// Just multiply new amount with cached value
			responseCurrencyExchange.setConvertedValue(cachedRate.multiply(request.getAmount()));
			return responseCurrencyExchange;
		}

		// Just adding access key because other services not subscribed and access key
		// not valid for others.
		MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<String, String>();
		uriVariables.add("access_key", ACCESS_KEY);

		// Preparing uri for rest template calls
		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(API_BASE_URL).queryParams(uriVariables).build();

		// initialize restTemplate
		RestTemplate restTemplate = new RestTemplate();

		// call rest api
		ExchangeRates rates = restTemplate.getForObject(uriComponents.toUri(), ExchangeRates.class);

		// if calling rest api is success then calculating currency with source,target
		// and amount information
		if (rates.getSuccess()) {

			BigDecimal sourceCurrency = rates.getRates().get(request.getSource().getCode());
			BigDecimal targetCurrency = rates.getRates().get(request.getTarget().getCode());

			BigDecimal divideResult = targetCurrency.divide(sourceCurrency, 4, RoundingMode.CEILING);

			responseCurrencyExchange.setConvertedValue(divideResult.multiply(request.getAmount()));
			cache.cacheRate(rateKey.toString(), divideResult);
		} else {
			// if calling web service is fail then setting error code and desc and converted
			// value setting with zero.
			Error error = rates.getError();
			responseCurrencyExchange.setCode(error.getCode());
			responseCurrencyExchange.setMessage(error.getMessage());
			responseCurrencyExchange.setConvertedValue(BigDecimal.ZERO);
		}

		return responseCurrencyExchange;
	}

	@Override
	public CurrencyModel getCurrencyModel() {
		return new CurrencyModel();
	}

}
