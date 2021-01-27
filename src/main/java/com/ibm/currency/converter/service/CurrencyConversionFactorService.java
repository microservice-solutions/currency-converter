package com.ibm.currency.converter.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.currency.converter.client.CurrencyConversionFactorClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class CurrencyConversionFactorService {

	@Autowired
	CurrencyConversionFactorClient client;
	
	@HystrixCommand(fallbackMethod = "getConversionFactorFallback")
	public ResponseEntity<JsonNode> getConversionFactor(Map<String, String> parameters) {
		return client.getConversionFactor(parameters);
	}
	
	public ResponseEntity<JsonNode> getConversionFactorFallback(Map<String, String> parameters) throws JsonMappingException, JsonProcessingException {
		String json = "{\"conversionFactor\": 0.0, \"countryCode\": \"NA\"}";

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(json);
		
		return ResponseEntity.ok(jsonNode);
	}
}
