package com.example.demo.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PAYMENT_COMPONENTS")
public class PaymentComponents
{
	@Id
	@GeneratedValue
	private int customerId;
	private int sourceAccountNumber;
	private int targetAccountNumber;
	private double baseAmmount;
	private double targetAmmount;
	private String baseCurrency;
	private String targetCurrency;
	private String timeStamp;
	private String transactionReferenceNumber;
	private String paymentStatus;
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getSourceAccountNumber() {
		return sourceAccountNumber;
	}
	public void setSourceAccountNumber(int sourceAccountNumber) {
		this.sourceAccountNumber = sourceAccountNumber;
	}
	public int getTargetAccountNumber() {
		return targetAccountNumber;
	}
	public void setTargetAccountNumber(int targetAccountNumber) {
		this.targetAccountNumber = targetAccountNumber;
	}
	public double getBaseAmmount() {
		return baseAmmount;
	}
	public void setBaseAmmount(double baseAmmount) {
		this.baseAmmount = baseAmmount;
	}
	public double getTargetAmmount() {
		return targetAmmount;
	}
	public void setTargetAmmount(double targetAmmount) {
		this.targetAmmount = targetAmmount;
	}
	public String getBaseCurrency() {
		return baseCurrency;
	}
	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	public String getTargetCurrency() {
		return targetCurrency;
	}
	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getTransactionReferenceNumber() {
		return transactionReferenceNumber;
	}
	public void setTransactionReferenceNumber(String transactionReferenceNumber) {
		this.transactionReferenceNumber = transactionReferenceNumber;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	@Override
	public String toString() {
		return "PaymentComponents [customerId=" + customerId + ", sourceAccountNumber=" + sourceAccountNumber
				+ ", targetAccountNumber=" + targetAccountNumber + ", baseAmmount=" + baseAmmount + ", targetAmmount="
				+ targetAmmount + ", baseCurrency=" + baseCurrency + ", targetCurrency=" + targetCurrency
				+ ", timeStamp=" + timeStamp + ", transactionReferenceNumber=" + transactionReferenceNumber
				+ ", paymentStatus=" + paymentStatus + "]";
	}
	
	
	
}
