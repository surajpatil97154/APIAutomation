package com.qa.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	private Integer id;
	private String title;
	private Double price;
	private String description;
	private String category;
	private String image;
	private Rating rating;
	
	@Builder
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Rating{
		private float rate;
		private Integer count;
	}
	

}
