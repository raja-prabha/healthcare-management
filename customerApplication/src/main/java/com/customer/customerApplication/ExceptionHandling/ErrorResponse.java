package com.customer.customerApplication.ExceptionHandling;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
 
/**
 * @author rajaprabha
 *
 */
@XmlRootElement(name = "error")
public class ErrorResponse 
{
    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }
  
    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

    private String message;
 
    private List<String> details;
 
    
}