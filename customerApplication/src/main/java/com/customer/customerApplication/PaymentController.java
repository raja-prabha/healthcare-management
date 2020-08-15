package com.customer.customerApplication;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController{
	
	@Autowired
	PaymentRepository payRepo;
	
	@Autowired
	ExceptionHandle exceptionHandling;
	
	
	
	/**
	 * @param payEntity
	 * @param id
	 * @param result
	 * @return
	 */
	@PostMapping("/updatePayment/{id}")
    public ResponseEntity<Object>  updatePayment(@Valid @RequestBody PaymentEntity payEntity,@PathVariable Long id,BindingResult result) {
		try {
			if(result.hasErrors()) {
				return exceptionHandling.handleMethodArgumentNotValid(result);
			}else {
		PaymentEntity payObject = payRepo.findByCustomerId(id);
		if(payObject == null) {
			PaymentEntity pay=payRepo.save(payEntity);
			return new ResponseEntity<Object>(pay, HttpStatus.OK);
			
		}
		else {
			payObject.setCustomerId(payEntity.getCustomerId());
			payObject.setPaidAmount(payObject.getPaidAmount()+payEntity.getPaidAmount());
	    	  payRepo.save(payObject);
	    	  return new ResponseEntity<Object>(payObject, HttpStatus.OK);
		}
	        
	}}
		catch(IllegalArgumentException exception){
     return exceptionHandling.handleAllExceptions(exception);
	}
		
	}
	
	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/getPayment/{id}")
    public ResponseEntity<Object>  getPayment(@PathVariable Long id) {
		try {
		List<String[]> obj = payRepo.findByCustomerPayment(id);
		if(obj.isEmpty()) {
			List<String> error = new ArrayList<>();
			error.add("Record not found");
			return exceptionHandling.handleUserNotFoundException(error);
		}
		else {
			String[] userDetails = obj.get(0);
			paymentResponse payResponse = new paymentResponse();
			payResponse.setId(String.valueOf(userDetails[0]));
			payResponse.setPaid(String.valueOf(userDetails[1]));
			payResponse.setTotal(String.valueOf(userDetails[2]));
			if(Float.valueOf((userDetails[1])) < Float.valueOf(userDetails[2]) ){
				payResponse.setExcess("0");
				payResponse.setDue(String.valueOf(Float.valueOf( userDetails[2] ) - Float.valueOf(userDetails[1])));
			}else{
				payResponse.setDue("0");
				payResponse.setExcess(String.valueOf(Float.valueOf(userDetails[1] ) - Float.valueOf( userDetails[2])));
			}
			
    	  return new ResponseEntity<Object>(payResponse, HttpStatus.OK);
		}
	        
	}
		catch(IllegalArgumentException exception){
     return exceptionHandling.handleAllExceptions(exception);
	}
		
	}
	
}