package com.org.ps.martek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.org.ps.martek.service.CustomerSagaService;


@RestController
public class CustomerSagaController {

	@Autowired
	CustomerSagaService service;

	
	@GetMapping("/reverse/{id}")
    public String swipeIn(@PathVariable(value = "id") Long employeeId) throws Exception {
			return service.inTimeAttendence(employeeId);
    }
}
