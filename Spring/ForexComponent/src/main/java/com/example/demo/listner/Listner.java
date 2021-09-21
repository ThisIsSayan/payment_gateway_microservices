package com.example.demo.listner;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;





@Service
public class Listner
{

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	private static final String TOPIC = "kafka_example2";
	private String response = null;
	
	
	@KafkaListener(topics="kafka_example", groupId="group_id")
	public void consume(String targetCurrency)
	{
		
		System.out.println("This is: " + targetCurrency + " In Listener");
		send(targetCurrency);
		
	}
	public void send(String targetCurrency)
	{
		if(targetCurrency.equals("USD"))
		{
			response = "0.013";
			
		}
		else if(targetCurrency.equals("EUR"))
		{
			response = "0.011";
			//kafkaTemplate.send(TOPIC, response);
		}
		else if(targetCurrency.equals("RUB"))
		{
			response = "1.01";
			//kafkaTemplate.send(TOPIC, response);
		}
		else if(targetCurrency.equals("BRL"))
		{
			response = "0.070";
			//kafkaTemplate.send(TOPIC, response);
		}
		else if(targetCurrency.equals("MVR"))
		{
			response = "0.21";
			//kafkaTemplate.send(TOPIC, "0.21");
		}
		else if(targetCurrency.equals("IDR"))
		{
			response = "195.26";
			//kafkaTemplate.flush();
			//kafkaTemplate.send(TOPIC, "195.26");
		}
		else if(targetCurrency.equals("GRD"))
		{
			response = "0.020";
			//kafkaTemplate.send(TOPIC, response);
		}
		else
		{
			response = null;
			//kafkaTemplate.send(TOPIC, response);
		}
		kafkaTemplate.send(TOPIC, response);
		System.out.println(response);
		response = null;
	}
}
