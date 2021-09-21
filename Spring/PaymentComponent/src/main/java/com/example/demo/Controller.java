package com.example.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.AuthRequest;
import com.example.demo.entity.AuthResponse;
import com.example.demo.util.JwtUtil;

@RestController
@RequestMapping("/")
public class Controller {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	private static final String TOPIC = "kafka_example";
	private PaymentComponents paymentComponent;
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    
	public double ta=0.0; 
	
	@PostMapping("/localPaymentRequest")
	public String doPayment(@RequestBody PaymentComponents localPayment)
	{
		localPayment.setTargetCurrency(localPayment.getBaseCurrency());
		localPayment.setTargetAmmount(localPayment.getBaseAmmount());
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<PaymentComponents> entity = new HttpEntity<PaymentComponents>(localPayment,headers);
	      
	    //return restTemplate.exchange(
	         //"http://localhost:8080/localPayment/doLocalPayments", HttpMethod.POST, entity, String.class).getBody();
	    return restTemplate.exchange(
	    	 "http://CoreBankingComponent/localPayment/doLocalPayments", HttpMethod.POST, entity, String.class).getBody();
	}
	@KafkaListener(topics="kafka_example2", groupId="group_id")
	public double consume(String targetCurrency)
	{
		ta = 0;
		ta = Double.parseDouble(targetCurrency);
		System.out.println("This is: " + ta);
		//paymentComponent.setTargetAmmount(ta);
		return ta;
	}
	@PostMapping("/crossPaymentRequest")
	public String doCrossPayment(@RequestBody PaymentComponents crossPayment)
	{
		//crossPayment.setTargetAmmount(500);
		kafkaTemplate.send(TOPIC, crossPayment.getTargetCurrency());
		//System.out.println("This is: " + ta + " In payment");
		//crossPayment.setTargetAmmount(ta);
		//ta = consume("");
		System.out.println("This is: " + ta+ " In payment");
		crossPayment.setTargetAmmount(ta*crossPayment.getBaseAmmount());
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<PaymentComponents> entity = new HttpEntity<PaymentComponents>(crossPayment,headers);

	    
		//System.out.println("Published successfull: " + crossPayment.getTargetCurrency());
	    //return restTemplate.exchange(
	         //"http://localhost:8080/localPayment/doLocalPayments", HttpMethod.POST, entity, String.class).getBody();
	    return restTemplate.exchange(
	    	 "http://CoreBankingComponent/crossPayment/doCrossPayments", HttpMethod.POST, entity, String.class).getBody();
	}
	@GetMapping("/response/{customerId}")
	public String getProductList(@PathVariable("customerId")int cid) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      String getUrl = "http://CoreBankingComponent/localPayment/localPaymentResponse/" + cid;
	      return restTemplate.exchange(
	    		  getUrl, HttpMethod.GET, entity, String.class).getBody();
	   }
	
	@PostMapping("/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        final String jwt = jwtUtil.generateToken(authRequest.getUserName());
        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
