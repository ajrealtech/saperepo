package com.org.ps.martek.data;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProductWorkflowRepository extends CrudRepository<ProductWorkFlow, Integer> {
	
	public static final String WORKFLOW = "select * from product_workflow where workflow_id= :id ";
	@Query(value = WORKFLOW, nativeQuery = true)
	public String getWorkflowfromId(@Param("id") String id );

	
	public static final String PREVIOUS_ID = "select max(id)+1 from product_workflow ";
	@Query(value = PREVIOUS_ID, nativeQuery = true)
	public Long getLastID();

	public static final String SET_PROCESSED = "update product_workflow set status = null where today_date = :today and employee_id= :empId ";
	@Modifying(clearAutomatically = true)
	@Query(value = SET_PROCESSED, nativeQuery = true)
	public void setProcessed(@Param("today") String today, @Param("empId") Long empId  );

}
