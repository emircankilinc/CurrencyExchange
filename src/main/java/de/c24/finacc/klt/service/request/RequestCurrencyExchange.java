package de.c24.finacc.klt.service.request;

import java.math.BigDecimal;

import de.c24.finacc.klt.util.Currency;

public class RequestCurrencyExchange {

	private Currency source;
	private Currency target;
	private BigDecimal amount;

	public RequestCurrencyExchange(Currency source, Currency target, BigDecimal amount) {
		super();
		this.source = source;
		this.target = target;
		this.amount = amount;
	}

	public Currency getSource() {
		return source;
	}

	public void setSource(Currency source) {
		this.source = source;
	}

	public Currency getTarget() {
		return target;
	}

	public void setTarget(Currency target) {
		this.target = target;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
