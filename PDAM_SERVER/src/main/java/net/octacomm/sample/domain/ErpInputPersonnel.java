package net.octacomm.sample.domain;

import lombok.Data;

@Data
public class ErpInputPersonnel implements Domain{
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//항타운전원
	private String driver;
	//반장
	private String captain;
	//조공+
	private String sculptor;
	//믹서공
	private String mixer;
	//용접사+
	private String welder;
	//신호수+
	private String signalman;
	//유도원+
	private String inducer;
	//용역
	private String prestation;
	//크레인+
	private String crane;
	//굴삭기+
	private String excavator;
	//로더
	private String loader;
	
}
