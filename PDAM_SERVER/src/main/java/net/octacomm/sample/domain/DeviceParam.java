package net.octacomm.sample.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class DeviceParam extends DomainParam {
	
	private int constructionIdx;
	
	private String searchField;
	
	private String searchWord;
	
	private String startDate;
	
	private String endDate;
	
}
