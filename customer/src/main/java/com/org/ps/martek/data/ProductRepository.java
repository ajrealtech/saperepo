package com.org.ps.martek.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProductRepository extends CrudRepository<Product, Integer> {
	
	public static final String GET_PRODUCT = "select * from Product where ProductCategory= :productCategory ";
	@Query(value = GET_PRODUCT, nativeQuery = true)
	public List<Product> getProduct(@Param("productCategory") String productCategory );

}
