package net.octacomm.sample.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class ReportParam extends DomainParam {
	
	private int id;
	
	private String type;
	
	private String date;
	
	private String mode;
	
	private String searchField;
	
	private String searchWord;
	
	private String startDate;
	
	private String endDate;
	
}
