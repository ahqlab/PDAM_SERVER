package net.octacomm.sample.domain;

import lombok.Data;

@Data
public class Group  implements Domain{
	
	private int idx;
	
	private String groupName;
	
	private String deviceAmount;
	
	private String cprtCompanyAmount;
	
	private int isDel;
	
	private String createDate;
	
	private String lastModifiedDate;
	
}
