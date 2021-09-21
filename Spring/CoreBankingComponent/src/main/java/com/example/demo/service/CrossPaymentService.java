package com.example.demo.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PaymentRepo;
import com.example.demo.entity.PaymentComponents;

@Service
public class CrossPaymentService
{
	@Autowired
	private PaymentRepo repo;
	public PaymentComponents doPayment(PaymentComponents crossPayment)
	{
		crossPayment.setTransactionReferenceNumber(UUID.randomUUID().toString());
		crossPayment.setPaymentStatus(setPaymentStatus());
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		crossPayment.setTimeStamp(df.format(dateobj).toString());
		return repo.save(crossPayment);
	}
	public String setPaymentStatus()
	{
		return new Random().nextBoolean() ? "Success" : "Fail";
	}
}
