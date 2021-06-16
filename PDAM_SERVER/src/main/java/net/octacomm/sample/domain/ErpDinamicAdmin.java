package net.octacomm.sample.domain;

import lombok.Data;


@Data
public class ErpDinamicAdmin implements Domain{
	
	private int edaIdx;
	//출력일
	private String printDate;
	
	private String name;
	
	private String value;
}
