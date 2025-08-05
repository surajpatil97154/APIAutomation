package com.qa.api.pojo;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	private String name;
	private String email;
	private String gender;
	private String status;
	

}
