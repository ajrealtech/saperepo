package com.org.ps.martek.dto;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@RedisHash("Product")
public class Product implements Serializable{

	private Long productId;
	
	private String productName;
	
	private String productCategory;
	
	private String productOwner;

	private Long cost;
}
