package com.luv2code.springcrmrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springcrmrest.entity.Customer;
import com.luv2code.springcrmrest.error.CustomerNotFoundException;
import com.luv2code.springcrmrest.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	@Autowired
	private CustomerService customerService;

	@GetMapping("/customers")
	public List<Customer> getCustomers(){
		return customerService.getCustomers();
	}
	
	@GetMapping("/customers/{idCustomer}")
	public Customer getCustomer(@PathVariable int idCustomer) {
		Customer customer = customerService.getCustomer(idCustomer);
		
		if(customer == null) {
			throw new CustomerNotFoundException("Customer id not found - " + idCustomer);
		}
		
		return customer;
	}
	
	@PostMapping("/customers")
	public Customer addCostumer(@RequestBody Customer customer) {
		customer.setId(0);
		customerService.saveCustomer(customer);
		
		return customer;
	}
	
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
		
		return customer;
	}
	
	@DeleteMapping("/customers/{idCustomer}")
	public String deleteCustomer(@PathVariable int idCustomer) {
		Customer customer = customerService.getCustomer(idCustomer);
		
		if(customer == null) {
			throw new CustomerNotFoundException("Customer id not found - " + idCustomer);
		}
		
		customerService.deleteCustomer(idCustomer);
		
		return "Deleted customer id - " + idCustomer;
	}
	
}
