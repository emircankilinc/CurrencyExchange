package de.c24.finacc.klt.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.c24.finacc.klt.service.intf.ICurrencyExchangeCallService;
import de.c24.finacc.klt.service.request.RequestCurrencyExchange;
import de.c24.finacc.klt.service.response.ResponseCurrencyExchange;
import de.c24.finacc.klt.util.Currency;

/**
 * RestServiceTest
 */

@SpringBootTest
class RestServiceTest {

	@Autowired
	private ICurrencyExchangeCallService currencyExchangeCallService;

	@Test
	@DisplayName("Simple test")
	void testit() {
		RequestCurrencyExchange request = new RequestCurrencyExchange(Currency.EUR, Currency.TRY, new BigDecimal(10));
		ResponseCurrencyExchange result = currencyExchangeCallService.getConvertedValue(request);
		assertThat(result).isNotNull();
	}
}
