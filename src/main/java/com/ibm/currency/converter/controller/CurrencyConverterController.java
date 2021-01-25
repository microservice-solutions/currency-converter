package com.ibm.currency.converter.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.ibm.currency.converter.client.CurrencyConversionFactorClient;
import com.ibm.currency.converter.model.CurrencyConvertedModel;


@RestController
@RequestMapping("/currency/converter")
public class CurrencyConverterController {
	
	@Autowired
	CurrencyConversionFactorClient client;
	
	@GetMapping
	public ResponseEntity<CurrencyConvertedModel> convertCurrency(
			@RequestParam(name = "country") String country, @RequestParam(name = "amount") float amount) {
		
		Map<String, String> parameters = new LinkedHashMap<>();
		parameters.put("country", country);
		
		ResponseEntity<JsonNode> result = client.getConversionFactor(parameters);
		
		String responseCountry = result.getBody().findValue("countryCode").asText();
		double responseFactor = result.getBody().findValue("conversionFactor").asDouble();
		double convertedAmount = amount*responseFactor;
		
		CurrencyConvertedModel response = new CurrencyConvertedModel();
		response.setCountry(responseCountry);
		response.setConvertedAmount(convertedAmount);
		
		return ResponseEntity.ok(response);
	}
}
