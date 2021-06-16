package net.octacomm.sample.domain;

import java.util.List;

import lombok.Data;

@Data
public class UpdateReport implements Domain {
	
	private int id;
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
	
	private String ultimateBearingCapacity;
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
	
	private List<Penetration> penetrations;
	//용접개소
	private String connectLength;
	//합계
	private String totalConnectWidth;
	//평균관입량
	private String avgPenetrationValue;
	
	private String totalPenetrationValue;
	
	private String createDate;
	
	private float gongSac;
   //단면적
    private String crossSection;
    //함마 효율
    private String hammaEfficiency;
    //탄성 계수
    private String modulusElasticity;

    private String machineNumber;
    
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
		return Float.parseFloat(getTotalConnectWidth()) - Float.parseFloat(getIntrusionDepth() != "" ? getIntrusionDepth() : "0");
	}
	//잔량
	public void setBalance(float balance) {
		this.balance = balance;
	}
	//이음계소
	public String getConnectLength() {
		int length = -1;
		for (Piece list : piece) {
			if(list.getValue() != "" && !list.getValue().equals("0")) {
				//System.err.println("list.getValue() :"  + list.getValue() );
				if(list.getName().equals("단본")) {
					length++;
				}else {
					length++;
				}
			}
		}
		//System.err.println("XXXXXXXXXXXXXXXXXXXXXXXXX : " + length);
		return String.valueOf(length == -1 ? 0 : length);
	}
	//이음계소
	public void setConnectLength(String connectLength) {  
		this.connectLength = connectLength;
	}
	//합계  
	public String getTotalConnectWidth() {
		float width = 0;
		for (Piece list : piece) {
			width += Float.parseFloat(list.getValue() != "" ? list.getValue() : "0");
		}
		return String.valueOf(width);
	}
	//합계
	public void setTotalConnectWidth(String totalConnectWidth) {
		this.totalConnectWidth = totalConnectWidth;
	}
	//평균관입량
	public String getAvgPenetrationValue() {
		float width = 0;
		try {
			for (Penetration penetration : penetrations) {
				width += Float.parseFloat(penetration.getValue() != "" ? penetration.getValue() : "0");
			}
			System.err.println("평균관입량 :  " + String.valueOf(width / penetrations.size()));
			return String.valueOf(String.format("%.1f" , width / penetrations.size()));
		}catch (NullPointerException e) {
			return avgPenetrationValue;
		}
	}
	public void setAvgPenetrationValue(String avgPenetrationValue) {
		this.avgPenetrationValue = avgPenetrationValue;
	}
	
	//최종관입량
	public String getTotalPenetrationValue() {
		float width = 0;
		try {
			for (Penetration penetration : penetrations) {
				width += Float.parseFloat(penetration.getValue() != "" ? penetration.getValue() : "0");
			}
			System.err.println("최종관입량 :" + String.valueOf(width));
			return String.valueOf(String.format("%.1f" , width));
		}catch (NullPointerException e) {
			return totalPenetrationValue;
		}
	}
	
	public void setTotalPenetrationValue(String totalPenetrationValue) {
		this.totalPenetrationValue = totalPenetrationValue;
	}
	
	
	public String getDrillingDepth() {
		return drillingDepth;
	}
	public void setDrillingDepth(String drillingDepth) {
		try {
			Float.parseFloat(drillingDepth);
			this.drillingDepth = drillingDepth;
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	public String getHammaT() {
		return hammaT;
	}
	public void setHammaT(String hammaT) {
		try {
			Float.parseFloat(hammaT);
			this.hammaT = hammaT;
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	public String getFallMeter() {
		return fallMeter;
	}
	public void setFallMeter(String fallMeter) {
		try {
			Float.parseFloat(fallMeter);
			this.fallMeter = fallMeter;
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	public String getManagedStandard() {
		return managedStandard;
	}
	public void setManagedStandard(String managedStandard) {
		try {
			Float.parseFloat(managedStandard);
			this.managedStandard = managedStandard;
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	public String getIntrusionDepth() {
		return intrusionDepth;
	}
	public void setIntrusionDepth(String intrusionDepth) {
		try {
			Float.parseFloat(intrusionDepth);
			this.intrusionDepth = intrusionDepth;
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

}
