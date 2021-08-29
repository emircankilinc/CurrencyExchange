package de.c24.finacc.klt.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.c24.finacc.klt.model.CurrencyModel;
import de.c24.finacc.klt.service.intf.ICurrencyExchangeCallService;
import de.c24.finacc.klt.service.request.RequestCurrencyExchange;
import de.c24.finacc.klt.service.response.ResponseCurrencyExchange;

/**
 * IndexController
 */
@Controller
public class IndexController {

	//Injection of currency exchange service
	@Autowired
	ICurrencyExchangeCallService currencyExchangeCallService;

	/**
	 * Index and currency attributes and properties
	 *
	 * @param model Spring's view model
	 * @return view name
	 */
	@GetMapping({ "/", "/converter" })
	public String index(Model model) {
		model.addAttribute("title", "Karten&Konten KLT");
		model.addAttribute("welcome", "Welcome to Check24");
		model.addAttribute("applicationTitle", "Check24 Currency Converter");
		model.addAttribute("currencyModel", currencyExchangeCallService.getCurrencyModel());
		return "index";
	}


	/**
	 * Call and set the result currency source to target i.e. EUR TO TRY
	 *
	 * @param currencyModel CurrencyModel
	 * @param model Model 
	 * @param request HttpServletRequest
	 * @return ResponseEntity<ResponseCurrencyExchange>
	 */
	@RequestMapping(value = "/convert", method = RequestMethod.POST)
	public ResponseEntity<ResponseCurrencyExchange> convertCurrencySourceToTarget(
			@ModelAttribute CurrencyModel currencyModel, Model model, HttpServletRequest request) {

		HttpStatus httpStatus = HttpStatus.OK;

		// setting currencyModel from html with js.
		model.addAttribute("currencyModel", currencyModel);

		// Currency Exchange service is called with selected currencies with amount.
		ResponseCurrencyExchange responseCurrencyExchange = currencyExchangeCallService
				.getConvertedValue(new RequestCurrencyExchange(currencyModel.getSourceCurrency(),
						currencyModel.getTargetCurrency(), currencyModel.getSourceAmount()));

		return new ResponseEntity<ResponseCurrencyExchange>(responseCurrencyExchange, httpStatus);
	}
}
