package com.org.ps.martek;

import com.org.ps.martek.controller.CustomerController;

import cucumber.api.java.en.Given;

public class CustomerServiceTest {

	CustomerController service = null;
	String percentage = "";
	
	@Given("^Execute DiscountService Business$")
	public void execute_DiscountService_Business() throws Throwable {
		service = new CustomerController();
	}
}
