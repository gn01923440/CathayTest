package com.example.demo.currencyDto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Currentprice {
	private Time time;
	private String disclaimer;
	private String chartName;
	private Map<String, Currency> bpi;

	@Data
	public static class Time {
		@JsonFormat(pattern = "MMM d, yyyy HH:mm:ss z", locale = "en_US", timezone = "UTC")
		private Date updated;
		@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
		private Date updatedISO;
		@JsonFormat(pattern = "MMM d, yyyy 'at' HH:mm z", locale = "en_GB")
		private Date updateduk;

	}

	@Data
	public static class Currency {
		private String code;
		private String symbol;
		private String rate;
		private String description;
		private double rate_float;

		public Currency() {
		}
	}
}
