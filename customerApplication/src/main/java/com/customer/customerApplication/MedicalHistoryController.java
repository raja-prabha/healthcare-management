package com.customer.customerApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rajaprabha
 *
 */
@RestController
public class MedicalHistoryController{
	
	
	@Autowired
	MedicalHistoryRepository medicalRepo; 
	
	@Autowired
	ExceptionHandle exceptionHandling;
	
	@Autowired
	PaymentRepository payRepo;
	
	/**
	 * @param medicalentity
	 * @param result
	 * @return
	 */
	@PostMapping("/addMedicalHistory")
	public ResponseEntity<Object> addMedicalHistory(@Valid @RequestBody MedicalHistoryEntity medicalentity,BindingResult result) {
		try {
        if(result.hasErrors()) {
        	return exceptionHandling.handleMethodArgumentNotValid(result);
        }else {
        	PaymentEntity payObject = payRepo.findByCustomerId(medicalentity.getCustomerId());
        	if(payObject == null) {
        		PaymentEntity payEntity= new PaymentEntity();
        		payEntity.setPaidAmount(0);
        		payEntity.setCustomerId(medicalentity.getCustomerId());
        		payRepo.save(payEntity);
        	}

        	medicalRepo.save(medicalentity);
        	return new ResponseEntity<Object>(medicalentity, HttpStatus.OK);
        }
		}
		catch(IllegalArgumentException exception){
			return exceptionHandling.handleAllExceptions(exception);
			
		}
			}
	
	/**
	 * @param medicalentity
	 * @param historyId
	 * @param result
	 * @return
	 */
	@PutMapping("/updateMedicalHistory/{historyId}")
    public ResponseEntity<Object>  editCustomer(@Valid @RequestBody MedicalHistoryEntity medicalentity,@PathVariable Long historyId,BindingResult result) {
		try {
			if(result.hasErrors()) {
				return exceptionHandling.handleMethodArgumentNotValid(result);
			}else {
		Optional<MedicalHistoryEntity> medicalObject = medicalRepo.findById(historyId);
		if(medicalObject.isPresent()) {
			medicalObject.ifPresent(medicalObj->{
				medicalObj.setDescription(medicalentity.getDescription());
				medicalObj.setStatus(medicalentity.getStatus());
	        	medicalRepo.save(medicalObj);
	});
			medicalentity.setHistoryId(historyId);
	        return new ResponseEntity<Object>(medicalentity, HttpStatus.OK);
		}
			else {
			List<String> error = new ArrayList<>();
			error.add("Medical Hstory not found");
			return exceptionHandling.handleUserNotFoundException(error);
		}

	}}
		catch(IllegalArgumentException exception){
		return exceptionHandling.handleAllExceptions(exception);
	}
		
	}
	
}