package com.org.ps.martek.data;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	
	public static final String GET_USER = "select username from customer where username= :username ";
	@Query(value = GET_USER, nativeQuery = true)
	public String getLogin(@Param("username") String username );

	
	public static final String PREVIOUS_ID = "select max(id)+1 from customer ";
	@Query(value = PREVIOUS_ID, nativeQuery = true)
	public Long getLastID();

	public static final String SET_PROCESSED = "update attendence_record set is_processed = null where today_date = :today and employee_id= :empId ";
	@Modifying(clearAutomatically = true)
	@Query(value = SET_PROCESSED, nativeQuery = true)
	public void setProcessed(@Param("today") LocalDate today, @Param("empId") Long empId  );

}
