package com.ibm.currency.converter.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.ibm.currency.converter.model.CurrencyConvertedModel;
import com.ibm.currency.converter.service.CurrencyConversionFactorService;


@RestController
@RequestMapping("/currency/converter")
public class CurrencyConverterController {
	
	private static final Logger log = LoggerFactory.getLogger(CurrencyConverterController.class);
	
	@Autowired
	CurrencyConversionFactorService service;
	
	@GetMapping
	public ResponseEntity<CurrencyConvertedModel> convertCurrency(
			@RequestParam(name = "country") String country, @RequestParam(name = "amount") float amount) {
		
		log.info("Received convert currency request for country {}", country);
		
		Map<String, String> parameters = new LinkedHashMap<>();
		parameters.put("country", country);
		
		ResponseEntity<JsonNode> result = service.getConversionFactor(parameters);
		
		String responseCountry = result.getBody().findValue("countryCode").asText();
		double responseFactor = result.getBody().findValue("conversionFactor").asDouble();
		double convertedAmount = amount*responseFactor;
		
		CurrencyConvertedModel response = new CurrencyConvertedModel();
		response.setCountry(responseCountry);
		response.setConvertedAmount(convertedAmount);
		
		return ResponseEntity.ok(response);
	}
}
