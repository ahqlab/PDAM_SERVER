package net.octacomm.sample.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class FileInventoryParam extends DomainParam {
	
	private String searchField;
	
	private String searchWord;
	
	private String startDate;
	
	private String endDate;
	
	private int constructionIdx;
	
	private String pileType;
}
