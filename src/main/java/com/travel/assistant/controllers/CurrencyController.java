package com.travel.assistant.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.assistant.enums.Currency;

@RestController
@RequestMapping("currency")
public class CurrencyController {
	
	@GetMapping("/all")
	public List<Currency> getAll(){
		return Arrays.asList(Currency.values());
	}
}
