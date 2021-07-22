package com.ssafy.happyhouse.model.dao;


import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.InterestAreaDto;
import com.ssafy.happyhouse.model.MemberDto;;

public interface MemberDao {
//	회원가입
	int registerMember(MemberDto memberDto);

//	로그인
	MemberDto login(MemberDto memberDto);

//	회원정보 수정을 위한 회원의 모든 정보 얻기
	MemberDto getMember(String userId);

//	회원정보 수정
	int modifyMember(MemberDto memberDto);

//	회원탈퇴
	int deleteMember(String userId);

//	관심지역추가
	String selInterestArea(String dong);
	
	List<InterestAreaDto> getInterestArea(String userid);
	
	int addInterestArea(InterestAreaDto interestAreaDto);
	
	int deleteInterestArea(InterestAreaDto interestAreaDto);
}
