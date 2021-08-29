package de.c24.finacc.klt.service.response;

import java.math.BigDecimal;

import de.c24.finacc.klt.model.Error;

public class ResponseCurrencyExchange extends Error {

	private BigDecimal convertedValue;

	public BigDecimal getConvertedValue() {
		return convertedValue;
	}

	public void setConvertedValue(BigDecimal convertedValue) {
		this.convertedValue = convertedValue;
	}

}
