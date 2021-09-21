package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.PaymentRepo;
import com.example.demo.entity.PaymentComponents;
import com.example.demo.service.LocalPaymentService;

@RestController
@RequestMapping("/localPayment")
public class LocalPaymentController
{
	@Autowired
	PaymentRepo repo;
	@Autowired
	private LocalPaymentService localService;
	
	@PostMapping("/doLocalPayments")
	public PaymentComponents doPayment(@RequestBody PaymentComponents localPayment)
	{
		return localService.doPayment(localPayment);
	}
	@GetMapping("/localPaymentResponse/{customerId}")
	public Optional<PaymentComponents> getLocalResponse(@PathVariable("customerId")int cid)
	{
		return repo.findById(cid);
	}
}
