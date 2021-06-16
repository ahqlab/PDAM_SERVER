package net.octacomm.sample.domain;

import lombok.Data;

@Data
public class AuthCode {

	private int id;
	
	private String userId;
	
	private String authCode;
		
}
