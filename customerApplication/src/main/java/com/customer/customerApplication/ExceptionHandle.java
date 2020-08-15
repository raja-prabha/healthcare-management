package com.customer.customerApplication;


import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.customer.customerApplication.ExceptionHandling.ErrorResponse;
@Component
public class ExceptionHandle{
	//To deal with all kind of exceptions
	/**
	 * @param ex
	 * @return
	 */
	public  ResponseEntity<Object> handleAllExceptions(Exception ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Server Error", details);
        return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

	//To deal with all validation exception
    /**
     * @param result
     * @return
     */
    public ResponseEntity<Object> handleMethodArgumentNotValid(BindingResult result) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : result.getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse("Validation Failed", details);
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
        
    }

  //To deal with the exception when the data is not  found
    /**
     * @param details
     * @return
     */
    public final ResponseEntity<Object> handleUserNotFoundException(List<String> details) {
        ErrorResponse error = new ErrorResponse("Record Not Found", details);
        return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
    }
    
    
}