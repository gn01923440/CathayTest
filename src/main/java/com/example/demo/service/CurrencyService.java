package com.example.demo.service;

import java.util.List;

import com.example.demo.currencyDto.Currentprice;
import com.example.demo.currencyDto.ResDto;
import com.example.demo.model.Currency;

public interface CurrencyService {
	List<Currency> search();

	ResDto searchByApi();

	Currentprice callCurrentPriceApi();

	void save(Currency dto);

	void deleteById(Long id);

}