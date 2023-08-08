package com.org.ps.martek.data;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Product")
//@RedisHash("Products")
public class Product implements Serializable {

	public Long getProductid() {
		return productid;
	}

	public void setProductid(Long productid) {
		this.productid = productid;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productid, productname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(productid, other.productid) && Objects.equals(productname, other.productname);
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getProductcategory() {
		return productcategory;
	}

	public void setProductcategory(String productcategory) {
		this.productcategory = productcategory;
	}

	public String getProductowner() {
		return productowner;
	}

	public void setProductowner(String productowner) {
		this.productowner = productowner;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	@Id
	@Column(name = "productid")
	private Long productid;
	
	
	@Column(name = "productname")
	private String productname;
	
	@Column(name = "productcategory")
	private String productcategory;
	
	@Column(name = "productowner")
	private String productowner;

	@Column(name = "cost")
	private Long cost;

}
