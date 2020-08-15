package com.customer.customerApplication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.sun.istack.NotNull;
@Entity
@Table(name ="PAYMENT")
public class PaymentEntity{
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long paymentId;
	
@Override
	public String toString() {
		return "PaymentEntity [paymentId=" + paymentId + ", customerId=" + customerId + ", paidAmount=" + paidAmount
				+ "]";
	}
    @Column(unique=true)
	@JoinColumn(name="customerId")
	private Long customerId;
    @Column
	 private float paidAmount;
    
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public float getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(float paidAmount) {
		this.paidAmount = paidAmount;
	}
	
}