package com.org.ps.martek.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.org.ps.martek.dto.Customer;
import com.org.ps.martek.dto.Product;
import com.org.ps.martek.service.CustomerService;

import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
public class CustomerController {
	
	  @Autowired
	 private   CustomerService customerService;
	  
	@PostMapping("/login")
	@TimeLimiter(name = "default")
	@Retry(name = "default")
    public CompletableFuture login(@RequestBody Customer customer) throws Exception {
		return CompletableFuture.supplyAsync(new Supplier<Customer>() {
			@Override
			public Customer get() {
				return customerService.login(customer);
			}
		});
    }
	
	@PostMapping("/sign-up")
	@TimeLimiter(name = "default")
	@Retry(name = "default")
    public CompletableFuture signup(@RequestBody Customer customer) throws Exception {
		return CompletableFuture.supplyAsync(new Supplier<String>() {
			@Override
			public String get() {
				return customerService.saveCustomer(customer);
			}
		});
    }
	
	@PostMapping("/sign-up-admin")
	@TimeLimiter(name = "default")
	@Retry(name = "default")
    public CompletableFuture signupAdmin(@RequestBody Customer customer) throws Exception {
		return CompletableFuture.supplyAsync(new Supplier<String>() {
			@Override
			public String get() {
				return customerService.saveAdmin(customer);
			}
		});
    }
	
	
	@PostMapping("/products")
	@TimeLimiter(name = "default")
	@Retry(name = "default")
    public CompletableFuture productList(@RequestBody Product product) throws Exception {
		return CompletableFuture.supplyAsync(new Supplier<List>() {
			@Override
			public List get() {
				return customerService.getProducts(product);
			}
		});
    }

}
