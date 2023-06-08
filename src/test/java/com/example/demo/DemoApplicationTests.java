package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.currencyDto.Currentprice;
import com.example.demo.currencyDto.ResDto;
import com.example.demo.model.Currency;
import com.example.demo.service.CurrencyService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class DemoApplicationTests {
	@Autowired
	private CurrencyService currencyService;

	@Test
    @Order(1)
	public void currencyServiceBySearch() {
		System.out.println(" ================== currencyServiceBySearch  測試開始 ==================");

		List<Currency> result = currencyService.search();
		Assert.assertNotNull(result);
		System.out.println(" ================== currencyServiceBySearch  測試結束 ==================");
		System.out.println(result.toString());
	}

	@Test
    @Order(2)
	public void currencyServiceByInsert() {
		System.out.println(" ================== currencyServiceByInsert  測試開始 ==================");

		currencyService.save(Currency.builder().code("test").codeCname("測試").build());
		List<Currency> result = currencyService.search();
		Optional<Currency> resultOptional = result.stream().filter(e -> e.getCode().equals("test")).findFirst();
		Assert.assertTrue(resultOptional.isPresent());
		System.out.println(resultOptional.get().toString());
		System.out.println(" ================== currencyServiceByInsert  測試結束 ==================");
	}

	@Test
    @Order(3)
	public void currencyServiceByUpdate() {
		System.out.println(" ================== currencyServiceByUpdate  測試開始 ==================");

		List<Currency> result = currencyService.search();
		Optional<Currency> resultOptional = result.stream().filter(e -> e.getCode().equals("test")).findFirst();
		Currency resultDto = resultOptional.get();
		resultDto.setCodeCname("測試-update");
		currencyService.save(resultDto);
		result = currencyService.search();
		resultOptional = result.stream().filter(e -> e.getCode().equals("test")).findFirst();
		Assert.assertTrue(resultOptional.isPresent());
		System.out.println(resultOptional.get().toString());
		System.out.println(" ================== currencyServiceByUpdate  測試結束 ==================");
	}

	@Test
    @Order(4)
	public void currencyServiceByDelete() {
		System.out.println(" ================== currencyServiceByDelete  測試開始 ==================");

		List<Currency> result = currencyService.search();
		Optional<Currency> resultOptional = result.stream().filter(e -> e.getCode().equals("test")).findFirst();
		Currency resultDto = resultOptional.get();
		currencyService.deleteById(resultDto.getId());
		result = currencyService.search();
		resultOptional = result.stream().filter(e -> e.getCode().equals("test")).findFirst();
		Assert.assertFalse(resultOptional.isPresent());
		System.out.println(result.toString());
		System.out.println(" ================== currencyServiceByDelete  測試結束 ==================");
	}

	@Test
    @Order(5)
	public void currencyServiceByCallCurrentpriceApi() {
		System.out.println(" ================== currencyServiceByCallCurrentpriceApi  測試開始 ==================");
		Currentprice result = currencyService.callCurrentPriceApi();
		Assert.assertNotNull(result);
		System.out.println(result.toString());
		System.out.println(" ================== currencyServiceByCallCurrentpriceApi  測試結束 ==================");
	}

	@Test
    @Order(6)
	public void currencyServiceBySearchByApi() {
		System.out.println(" ================== currencyServiceBySearchByApi  測試開始 ==================");
		ResDto result = currencyService.searchByApi();
		Assert.assertNotNull(result);
		System.out.println(result.toString());
		System.out.println(" ================== currencyServiceBySearchByApi  測試結束 ==================");
	}

}
