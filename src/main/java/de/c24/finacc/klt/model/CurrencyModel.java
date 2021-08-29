package de.c24.finacc.klt.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import de.c24.finacc.klt.util.Currency;

//Holding currency information with html page and sending to web service
public class CurrencyModel {

	private Currency sourceCurrency;
	private Currency targetCurrency;
	private BigDecimal sourceAmount;
	private BigDecimal targetAmount;

	public Currency getSourceCurrency() {
		return sourceCurrency;
	}

	public void setSourceCurrency(Currency sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}

	public Currency getTargetCurrency() {
		return targetCurrency;
	}

	public void setTargetCurrency(Currency targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

	public BigDecimal getSourceAmount() {
		return sourceAmount;
	}

	public void setSourceAmount(BigDecimal sourceAmount) {
		this.sourceAmount = sourceAmount;
	}

	public BigDecimal getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(BigDecimal targetAmount) {
		this.targetAmount = targetAmount;
	}

	
	//Listing all supported currencies from html side to filling cmbx
	public List<Currency> getSupportedCurrencies() {
		return Arrays.asList(Currency.values());
	}

}
