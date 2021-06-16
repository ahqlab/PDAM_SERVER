package net.octacomm.sample.domain;

import lombok.Data;

@Data
public class Construction implements Domain{

	//식별자
	private int id;
	//권한
	private int role;
	//시공사
	private String name;
	//현장명
	private String location;
	//현장주소
	private String address;
	//현장 담당자
	private String manager;
	//연락처
	private String contact;
	//아이디
	private String userId;
	//비밀번호
	private String password;
	//보안코드
	private String secretCode;
	//그룹아이디
	private int groupIdx;
	//생성일자
	private String createDate;
	//삭제여부
	private int isDel;
	//사업시행여부
	private int conduct;
}


