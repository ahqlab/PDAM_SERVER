package net.octacomm.sample.domain;

import lombok.Data;

@Data
public class AuthResult implements Domain{
	
	private String status;
	
	private String authCode;
	
}
