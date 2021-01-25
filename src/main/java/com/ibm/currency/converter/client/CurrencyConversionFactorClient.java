package com.ibm.currency.converter.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonNode;

import feign.QueryMap;

@FeignClient(name = "CURRENCYCONVERSIONFACTOR", url = "http://localhost:8080/")
public interface CurrencyConversionFactorClient {
	
	@RequestMapping(value = "/currency/conversion/factor?{parameters}", produces = { "*/*" }, method = RequestMethod.GET)
	ResponseEntity<JsonNode> getConversionFactor(@SpringQueryMap Map<String, String> parameters);
}
