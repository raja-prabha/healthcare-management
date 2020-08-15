package com.customer.customerApplication;


import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rajaprabha
 *
 */
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistoryEntity, Long>{
	

}