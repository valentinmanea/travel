package com.travel.assistant.enums;

import java.util.HashMap;
import java.util.Map;

public enum Currency {
	EUR("€"), USD("$"), GBP("£"), LEI("RON");
	
	private Currency(String symbol) {
		this.symbol = symbol;
	}

	private String symbol;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	private static final Map<String, Currency> map;
	  static {
	    map = new HashMap<String, Currency>();
	    for (Currency v : Currency.values()) {
	      map.put(v.symbol, v);
	    }
	  }
	  public static Currency findByKey(String symbol) {
	    return map.get(symbol);
	  }
}
