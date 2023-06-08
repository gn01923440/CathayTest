package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

}
