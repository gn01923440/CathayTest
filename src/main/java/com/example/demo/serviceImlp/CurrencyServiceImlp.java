package com.example.demo.serviceImlp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.currencyDto.Currentprice;
import com.example.demo.currencyDto.ResDto;
import com.example.demo.model.Currency;
import com.example.demo.repository.CurrencyRepository;
import com.example.demo.service.CurrencyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CurrencyServiceImlp implements CurrencyService {

	@Autowired
	CurrencyRepository CurrencyInfo;

	@Override
	public List<Currency> search() {
		return CurrencyInfo.findAll();
	}

	@Override
	public void deleteById(Long id) {
		CurrencyInfo.deleteById(id);
	}

	@Override
	public void save(Currency dto) {
		CurrencyInfo.save(dto);
	}

	@Override
	public ResDto searchByApi() {
		List<Currency> codeMapping = CurrencyInfo.findAll();
		Currentprice currentprice = this.callCurrentPriceApi();

		Date updated = currentprice.getTime().getUpdated();
		ResDto resDto = new ResDto();
		resDto.setUpdated(updated);
		resDto.setCurrentInfoList(new ArrayList<ResDto.CurrentInfo>());
		for (Currency dto : codeMapping) {
			Currentprice.Currency apiCurrency = currentprice.getBpi().get(dto.getCode());
			if (apiCurrency != null) {
				String rate = currentprice.getBpi().get(dto.getCode()).getRate();
				resDto.getCurrentInfoList().add(ResDto.CurrentInfo.builder().rate(rate).code(dto.getCode())
						.codeCname(dto.getCodeCname()).build());
			}

		}
		return resDto;
	}

	@Override
	public Currentprice callCurrentPriceApi() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
		String response = restTemplate.getForObject(url, String.class);
		ObjectMapper objectMapper = new ObjectMapper();
		Currentprice currentprice = new Currentprice();
		try {
			currentprice = objectMapper.readValue(response, Currentprice.class);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return currentprice;
	}
}
