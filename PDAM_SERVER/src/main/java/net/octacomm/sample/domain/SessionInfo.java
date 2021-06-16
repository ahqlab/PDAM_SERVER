package net.octacomm.sample.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SessionInfo implements Domain{
	
	private String userId;
	
	private int constructionIdx;
	
	private int role;
	
	private String constructionName;
	
	private boolean hiddenManager;
	
	private int groupIdx;

}
