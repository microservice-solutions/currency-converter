package com.ibm.currency.converter.model;

import lombok.Data;

@Data
public class CurrencyConvertedModel {
	
	private String country;
	private Double convertedAmount;
}
