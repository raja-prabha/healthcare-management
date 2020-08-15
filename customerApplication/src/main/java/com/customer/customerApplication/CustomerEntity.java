package com.customer.customerApplication;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;

import com.sun.istack.NotNull;

@Entity
@Table(name ="CUSTOMER")
public class CustomerEntity{
	
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long customerId;
	
	@NotEmpty(message = "First name is required")
    @Length(min =3,max=20 ,message = "Length should be 3-20")
	@Pattern(regexp = "[a-zA-Z0-9-]+", message = "ENTER VALID FIRST NAME")
	private String first_name;
	
	@NotEmpty(message = "Last name is required")
    @Length(min =3,max=20,message = "Length should be 3-20")
	@Pattern(regexp = "[a-zA-Z0-9-]+", message = "ENTER VALID LAST NAME")
	private String last_name;
	
	@Override
	public String toString() {
		return "CustomerEntity [customerId=" + customerId + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", phone_number=" + phone_number + ", email_id=" + email_id + ", signup_date=" + signup_date + "]";
	}

	@NotEmpty
	@Pattern(regexp="(^$|[0-9]{10})",message = "Invalid Mobile Number")
	private String phone_number;
	
	@NotEmpty
	@Column(unique=true)
	@Email(message = "Email shoulb be valid")
	private String email_id;
	
	@CreatedDate
	  private LocalDateTime signup_date;
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public LocalDateTime getSignup_date() {
		return signup_date;
	}

	public void setSignup_date(LocalDateTime signup_date) {
		this.signup_date = signup_date;
	}



	
}