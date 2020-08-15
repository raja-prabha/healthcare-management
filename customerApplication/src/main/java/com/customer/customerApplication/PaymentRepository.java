package com.customer.customerApplication;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author rajaprabha
 *
 */
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long>{
	
	
	 @Query(value = "SELECT * FROM PAYMENT WHERE CUSTOMER_ID = ?1", nativeQuery = true)
     PaymentEntity findByCustomerId(Long id);
	 
	 @Query(value = "SELECT pay.PAYMENT_ID ,SUM(pay.PAID_AMOUNT),SUM(hist.AMOUNT) FROM PAYMENT pay,MEDICAL_HISTORY hist WHERE pay.CUSTOMER_ID =hist.CUSTOMER_ID AND pay.CUSTOMER_ID = ?1 GROUP BY pay.CUSTOMER_ID ,hist.CUSTOMER_ID  ORDER BY pay.CUSTOMER_ID", nativeQuery = true )
	 List<String[]>  findByCustomerPayment(Long id); 
}