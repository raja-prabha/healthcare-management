package com.customer.customerApplication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name ="MEDICAL_HISTORY")
public class MedicalHistoryEntity{
	
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long historyId;
	
	@JoinColumn(name="customerId")
	private Long customerId;
	
	public enum Type {
	    DENTIST,
	    CARDIOLOGIST,
	   ALLERGIST,
	   AUDIOLOGIST
	}
	
	@Column(nullable =false)
	@Enumerated(EnumType.STRING)
	private Type type;
	@Override
	public String toString() {
		return "MedicalHistoryEntity [historyId=" + historyId + ", customerId=" + customerId + ", type=" + type
				+ ", status=" + status + ", description=" + description + ", amount=" + amount + "]";
	}
	public enum Status {
	    INPROGRESS,
	    DONE,
	   BLOCKED,
	   NOTSTARTED
	}
	
	@Column(nullable =false)
	@Enumerated(EnumType.STRING)
	private Status status;
	 
	@Column
	 private String description;
	
	@Column
	 private float amount;
	 
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public Long getHistoryId() {
		return historyId;
	}
	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}