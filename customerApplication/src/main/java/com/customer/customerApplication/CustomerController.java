package com.customer.customerApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomerController{
	
	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	ExceptionHandle exceptionHandling;
	
	//1.API Endpoints(Create Customer)
	
	/**
	 * @param customerentity
	 * @param result
	 * @return
	 */
	@PostMapping("/createCustomer")
	public ResponseEntity<Object> createCustomer(@Valid @RequestBody CustomerEntity customerentity,BindingResult result) {
		try {
        if(result.hasErrors()) {
        	return exceptionHandling.handleMethodArgumentNotValid(result);
        }else {
            LocalDateTime currentTime = LocalDateTime.now();
        	customerentity.setSignup_date(currentTime);
        	 customerRepo.save(customerentity);
        	 return new ResponseEntity<Object>(customerentity, HttpStatus.OK);
        }
		}
		catch(IllegalArgumentException exception){
			return exceptionHandling.handleAllExceptions(exception);
			
		}
			}


	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/getOneCustomer/{id}")
	Optional<CustomerEntity> getOneCustomer(@PathVariable Long id) {
		Optional<CustomerEntity> obj = customerRepo.findById(id);
	return obj;
		
	}
	/**
	 * @return
	 */
	@GetMapping("/getCustomerDetails")
	List<CustomerEntity> getCustomerDetails(){
		return customerRepo.findAll();
	}
	
	//2.API Endpoints(Edit Profile(only name))
	
	/**
	 * @param customerentity
	 * @param id
	 * @param result
	 * @return
	 */
	@PutMapping("/editCustomer/{id}")
      public ResponseEntity<Object>  editCustomer(@Valid @RequestBody CustomerEntity customerentity,@PathVariable Long id,BindingResult result) {
		try {
			if(result.hasErrors()) {
				return exceptionHandling.handleMethodArgumentNotValid(result);
			}else {
		Optional<CustomerEntity> cusObject = customerRepo.findById(id);
		if(cusObject.isPresent()) {
	        cusObject.ifPresent(cus->{
	        	cus.setFirst_name(customerentity.getFirst_name());
	        	cus.setLast_name(customerentity.getLast_name());
			 customerRepo.save(cus);
	});
	        return new ResponseEntity<Object>(customerentity, HttpStatus.OK);
		}
			else {
			List<String> error = new ArrayList<>();
			error.add("User not found");
			return exceptionHandling.handleUserNotFoundException(error);
		}

	}}
		catch(IllegalArgumentException exception){
		return exceptionHandling.handleAllExceptions(exception);
	}
		
	}
}