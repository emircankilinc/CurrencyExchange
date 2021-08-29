package de.c24.finacc.klt.cache;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;


//Holding currency and it's value at cache with specific duration
@Component
public class CurrencyCache {

	//Duration of cache time i.e. 60 minutes
	@Value("${exchange.api.cache.expiry}")
	private int CACHE_DURATION;

	private Cache<String, BigDecimal> cache;

	@PostConstruct
	public void init() {
		if (cache == null) {
			cache = CacheBuilder.newBuilder().expireAfterWrite(CACHE_DURATION, TimeUnit.MINUTES).build();
		}
	}

	
	//Get Cache Rate if given code is already in cache time interval
	public BigDecimal getCachedRate(String code) {
		return cache.getIfPresent(code);
	}

	//Adding rate to cacahe with it's value
	public void cacheRate(String code, BigDecimal rate) {
		cache.put(code, rate);
	}
}
