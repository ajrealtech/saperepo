package com.org.ps.martek.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.ps.martek.data.CustomerRepository;

@Service
public class CustomerSagaService {
	
	@Autowired
	CustomerRepository repository;

	Logger log = LoggerFactory.getLogger(CustomerService.class);
	

	public String inTimeAttendence(Long id) {
		repository.setProcessed(LocalDate.now(), id);
		return "Success";
	}
	

}
