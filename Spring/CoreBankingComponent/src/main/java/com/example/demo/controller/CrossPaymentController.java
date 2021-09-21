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
import com.example.demo.service.CrossPaymentService;

@RestController
@RequestMapping("/crossPayment")
public class CrossPaymentController
{
	@Autowired
	PaymentRepo repo;
	@Autowired
	private CrossPaymentService crossService;
	
	@PostMapping("/doCrossPayments")
	public PaymentComponents doPayment(@RequestBody PaymentComponents crossPayment)
	{
		return crossService.doPayment(crossPayment);
	}
	@GetMapping("/crossPaymentResponse/{customerId}")
	public Optional<PaymentComponents> getCrossResponse(@PathVariable("customerId")int cid)
	{
		return repo.findById(cid);
	}
}
