package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PaymentComponents;

public interface PaymentRepo extends JpaRepository<PaymentComponents, Integer>{

}
