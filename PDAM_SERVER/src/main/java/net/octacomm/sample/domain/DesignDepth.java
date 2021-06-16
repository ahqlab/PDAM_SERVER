package net.octacomm.sample.domain;

import java.util.List;

import lombok.Data;

@Data
public class DesignDepth implements Domain {
	
	private int ddIdx;
	//파일종류 2020-05-13 new
	private String pileType;
	//공법 2020-05-13 new
	private String method;
	//위치
	private String location;
	//파일넘버
	private String pileNo;
	//파일규격
	private String pileStandard;
	//설계심도
	private String designDepth;
	//
	private String deviceIdx;

}
