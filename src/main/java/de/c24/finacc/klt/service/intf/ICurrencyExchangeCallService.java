package de.c24.finacc.klt.service.intf;

import org.springframework.stereotype.Service;

import de.c24.finacc.klt.model.CurrencyModel;
import de.c24.finacc.klt.service.request.RequestCurrencyExchange;
import de.c24.finacc.klt.service.response.ResponseCurrencyExchange;

@Service
public interface ICurrencyExchangeCallService {

	ResponseCurrencyExchange getConvertedValue(RequestCurrencyExchange request);

	CurrencyModel getCurrencyModel();

}
	