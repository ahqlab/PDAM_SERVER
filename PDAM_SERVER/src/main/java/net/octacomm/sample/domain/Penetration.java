package net.octacomm.sample.domain;

import lombok.Data;

@Data
public class Penetration implements Domain{
	
	private int id;
	
	private int reportIdx;
	
	private String name;
	
	private String value;

}
