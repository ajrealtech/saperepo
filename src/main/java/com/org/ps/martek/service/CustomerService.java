package com.org.ps.martek.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.ps.martek.data.Customer;
import com.org.ps.martek.data.CustomerRepository;
import com.org.ps.martek.data.Product;
import com.org.ps.martek.data.ProductRepository;
import com.org.ps.martek.data.ProductWorkFlow;
import com.org.ps.martek.data.ProductWorkflowRepository;
import com.org.ps.martek.exception.DuplicateUserException;


@Service
public class CustomerService {

	@Autowired
	CustomerRepository repository;
	
	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductWorkflowRepository productWorkflowRepository;

	
	Logger log = LoggerFactory.getLogger(CustomerService.class);
	
	
	public com.org.ps.martek.dto.Customer login(com.org.ps.martek.dto.Customer customer) {
		String user = repository.getLogin(customer.getUsername());
		com.org.ps.martek.dto.Customer response = new com.org.ps.martek.dto.Customer();
		if(user!=null) {
		response.setName(customer.getName());
		response.setUsername(customer.getUsername());
		response.setFlowId(UUID.randomUUID().toString());
		workflow(response);
		}
		return user!=null ?response:null;
	}
	
	private void workflow(com.org.ps.martek.dto.Customer response) {
		ProductWorkFlow productWorkflow = new ProductWorkFlow();
		String message="";
		try {
			 message = new ObjectMapper().writeValueAsString(response);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long id = productWorkflowRepository.getLastID();
		productWorkflow.setId(id==null?1:id);
		productWorkflow.setMessage(message);
		productWorkflow.setToday(LocalDate.now().toString());
		productWorkflow.setWorkflowId(response.getFlowId());
		productWorkflow.setStatus("Progress");
		productWorkflow.setUsername(response.getUsername());
		productWorkflowRepository.save(productWorkflow);
	}
	
	public String saveCustomer(com.org.ps.martek.dto.Customer customer) throws DuplicateUserException  {
		Customer record = new Customer();
		Long id=repository.getLastID();
		record.setId(id);
		record.setName(customer.getName());
		record.setPassword(customer.getPassword());
		record.setPhone(customer.getPhone());
		record.setRole("CUST");
		record.setUsername(customer.getUsername());
		try {
			repository.save(record);
		}
		catch(DataIntegrityViolationException e) {
			throw  new DuplicateUserException("") ;
		}
		return "Thanks for Registration.";
	}
	
	
	public String saveAdmin(com.org.ps.martek.dto.Customer customer) throws DuplicateUserException  {
		Customer record = new Customer();
		Long id=repository.getLastID();
		record.setId(id);
		record.setName(customer.getName());
		record.setPassword(customer.getPassword());
		record.setPhone(customer.getPhone());
		record.setRole("ADMIN");
		record.setUsername(customer.getUsername());
		try {
			repository.save(record);
		}
		catch(DataIntegrityViolationException e) {
			throw  new DuplicateUserException("") ;
		}
		return "Thanks for Registration.";
	}
	
    @Cacheable(key = "#product",value = "Product",unless = "#product.cost > 100000")
	public List getProducts(com.org.ps.martek.dto.Product product)  {
		Product record = new Product();
		record.setProductcategory(product.getProductCategory());
		return productRepository.getProduct(product.getProductCategory());
	}
}
