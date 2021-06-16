package net.octacomm.sample.domain;

import lombok.Data;

@Data
public class ErpAdmin implements Domain{
	
	private int eaidx;
	//출력일
	private String printDate;
	//소장
	private String manager;
	//공무
	private String publicService;
	//공사
	private String construction;
	//안전
	private String safety;
	//측량
	private String measurement;
	//두부정리
	private String pileCuttingWork;
	//디바이스 아이디
	private int deviceIdx;
	
}
