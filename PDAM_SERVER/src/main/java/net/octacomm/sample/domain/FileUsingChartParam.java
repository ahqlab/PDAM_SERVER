package net.octacomm.sample.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class FileUsingChartParam extends DomainParam {
	
	private String pile;
	
	private String name;
	
	private String dateTime;
	
	FileUsingChartParam() {
	}
	
	public FileUsingChartParam(int constructionIdx, String pile, String name, String dateTime) {
		this.constructionIdx = constructionIdx;
		this.pile = pile;
		this.name = name;
		this.dateTime = dateTime;
	}

	
}
