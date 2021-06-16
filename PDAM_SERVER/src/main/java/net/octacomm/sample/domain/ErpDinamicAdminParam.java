package net.octacomm.sample.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class ErpDinamicAdminParam extends DomainParam {
	
	private String searchField;
	
	private String searchWord;
	
	private String startDate;
	
	private String endDate;
	
}
