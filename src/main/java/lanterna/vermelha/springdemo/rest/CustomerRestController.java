package lanterna.vermelha.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lanterna.vermelha.springdemo.entity.Customer;
import lanterna.vermelha.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	//autowire the Customer Service
	@Autowired
	private CustomerService customerService;
	
	//add mapping fot GET /customers
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}
	
	//add mapping for GET single customer
	@GetMapping("/customer/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		
		if (customerService.getCustomer(customerId) == null) {
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}
		return customerService.getCustomer(customerId);
	}
	
	//add mapping for POST /customer - add new customer
	@PostMapping("/customer")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		
		//if the id is 0 the dao will insert a new customer (instead of update)
		
		theCustomer.setId(0);
		customerService.saveCustomer(theCustomer);
		
		return theCustomer;
	}

}
