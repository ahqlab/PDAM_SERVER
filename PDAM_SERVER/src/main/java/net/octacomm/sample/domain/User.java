package net.octacomm.sample.domain;

import lombok.Data;

@Data
public class User implements Domain{
	
	private String id;
	
	private String password;

	private String name;

	private String department;
	
	private String email;

	private String phone;
}
