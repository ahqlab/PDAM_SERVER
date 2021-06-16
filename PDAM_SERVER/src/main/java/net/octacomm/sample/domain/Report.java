package net.octacomm.sample.domain;

import java.util.List;

import lombok.Data;

@Data
public class Report implements Domain {
	
	private int rownum;
	
	private int id;
	
	private int deviceIdx;
	//시공일자
	private String currentDateTime;
	//파일종류 2020-05-13 new
	private String pileType;
	//공법 2020-05-13 new
	private String method;
	//파일규격
	private String pileStandard;
	//위치
	private String location;
	//헤머무게
	private String hammaT;
	//낙하높이
	private String fallMeter;
	//관리기준
	private String managedStandard;
	//잔량
	private float balance;
	//파일넘버
	private String pileNo;
	//천공깊이
	private String drillingDepth;
	//관잎깊이
	private String intrusionDepth;
	//조각
	private List<Piece> piece;
	//용접개소
	private String connectLength;
	//합계
	private String totalConnectWidth;
	
	private List<Penetration> penetrations;
	//평균관입량
	private String avgPenetrationValue;
	//최종관입량
	private String totalPenetrationValue;
	//join
	private String name;
	//join
	private String lavelNo;
	//join
	private String createDate;
	//극한 지지력
	private String ultimateBearingCapacity;
    //단면적
    private String crossSection;
    //함마 효율
    private String hammaEfficiency;
    //탄성 계수
    private String modulusElasticity;

	private float gongSac;
	
	private String bigo;

	public float getGongSac() {
		return Float.parseFloat(String.format("%.1f", gongSac));
	}
	public void setGongSac(float gongSac) {
		this.gongSac = gongSac;
	}
	//잔량
	public float getBalance() {
		float value = Float.parseFloat(getTotalConnectWidth()) - Float.parseFloat(getIntrusionDepth() != "" ? getIntrusionDepth() : "0");
		if(value < 0)
		{
			setGongSac(value);
			return 0;
		}
		float result = Float.parseFloat(getTotalConnectWidth()) - Float.parseFloat(getIntrusionDepth() != "" ? getIntrusionDepth() : "0");
		return Float.parseFloat(String.format("%.1f", result));
	}
	//잔량
	public void setBalance(float balance) {
		this.balance = balance;
	}
	
}
