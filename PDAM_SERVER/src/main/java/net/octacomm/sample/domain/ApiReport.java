package net.octacomm.sample.domain;

import java.util.List;

import lombok.Data;


@Data
public class ApiReport implements Domain {
	
	private String 현장명;	
	//호기
	private String 기기번호;
	//시공일자
	private String 시공일자;
	
	private String 파일종류;
	
	private String 공법;
	
	private String 파일규격;
	
	private String 위치;
	//헤머무게
	private String 헤머무게;
	//낙하높이
	private String 낙하높이;
	//관리기준
	private String 관리기준;
	//잔량
	private float 잔량;
	//파일넘버
	private String 파일번호;
	//천공깊이
	private String 천공깊이;
	//관잎깊이
	private String 관입깊이;
	//조각
	private List<Piece> 파일;
	//용접개소
	private String 용접개소;
	//합계
	private String 합계;
	
	private List<Penetration> 측정;
	//평균관입량
	private String 평균관입량;
	//최종관입량
	private String 최종관입량;
	//극한 지지력
	private String 극한지지력;
    //단면적
    private String 단면적;
    //함마효율
    private String 헤머효율;
    //탄성계수
    private String 탄성계수;
    //공삭공
	private float 공삭공;
	
	
	public float get공삭공() {
		return Float.parseFloat(String.format("%.1f", 공삭공));
	}
	public void set공삭공(float 공삭공) {
		this.공삭공 = 공삭공;
	}
	
	public float get잔량() {
		float value = Float.parseFloat(get최종관입량()) - Float.parseFloat(get관입깊이() != "" ? get관입깊이() : "0");
		if(value < 0)
		{
			set공삭공(value);
			
			return 0;
		}
		float result = Float.parseFloat(get최종관입량()) - Float.parseFloat(get관입깊이() != "" ? get관입깊이() : "0");
		return Float.parseFloat(String.format("%.1f", result));
	}
}
