package com.sapient.income.model;

import java.util.HashMap;
import java.util.Map;

public class SalaryModel {
	private String amount;
	private String currency;
	private String city;
	private String gender;
	private String country;

	public String currencyConversion() {
		double amt=Double.parseDouble(this.amount);
		Map<String, Double> rates = new HashMap<>();
		rates.put("INR", 66.0);
		rates.put("GBP", 0.67);
		rates.put("SGD", 1.5);
		rates.put("HKD", 8.0);
		rates.put("USD", 1.0);
		return conversion(amt, rates.get(this.currency.toUpperCase()));
	}

	public String conversion(Double amt, Double rate) {
		return Double.toString(amt/rate);		
	}


	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}

}