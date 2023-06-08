package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Currency;
import com.example.demo.service.CurrencyService;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
	@Autowired
	private CurrencyService currencyService;

	@GetMapping()
	public ResponseEntity<?> search() {
		return ResponseEntity.ok(currencyService.search());
	}

	@GetMapping("/searchByApi")
	public ResponseEntity<?> searchByApi() {
		return ResponseEntity.ok(currencyService.searchByApi());
	}
	@GetMapping("/callCurrentPriceApi")
	public ResponseEntity<?> callCurrentpriceApi() {
		return ResponseEntity.ok(currencyService.callCurrentPriceApi());
	}
	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody Currency dto) {
		currencyService.save(dto);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("")
	public ResponseEntity<?> deleteById(@RequestParam Long id) {
		currencyService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
