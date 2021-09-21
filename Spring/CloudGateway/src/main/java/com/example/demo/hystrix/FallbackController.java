package com.example.demo.hystrix;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FallbackController
{
	@RequestMapping("/fallback")
	public String fallbackMessage()
	{
		return "Payment Microservice is taking too long to respond or is down. Please try again later";
	}
}
