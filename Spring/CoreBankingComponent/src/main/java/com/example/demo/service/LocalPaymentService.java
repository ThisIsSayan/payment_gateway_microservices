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
public class LocalPaymentService
{
	@Autowired
	private PaymentRepo repo;
	public PaymentComponents doPayment(PaymentComponents localPayment)
	{
		localPayment.setTransactionReferenceNumber(UUID.randomUUID().toString());
		localPayment.setPaymentStatus(setPaymentStatus());
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		localPayment.setTimeStamp(df.format(dateobj).toString());
		return repo.save(localPayment);
	}
	public String setPaymentStatus()
	{
		return new Random().nextBoolean() ? "Success" : "Fail";
	}
}
