package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.InterestAreaDto;
import com.ssafy.happyhouse.model.MemberDto;;

public interface MemberService {

//	회원가입
	boolean registerMember(MemberDto memberDto);
	
//	로그인
	MemberDto login(String userId, String userPwd);
	
//	회원정보 수정을 위한 회원의 모든 정보 얻기
	MemberDto getMember(String userId);
	
//	회원정보 수정
	boolean modifyMember(MemberDto memberDto);
	
//	회원탈퇴
	boolean deleteMember(String userId);
	
//	관심지역추가
	String selInterestArea(String dong);
	
	List<InterestAreaDto> getInterestArea(String userid);
	
	boolean delteteInterestArea(InterestAreaDto interestAreaDto);

	boolean addInterestArea(InterestAreaDto interestAreaDto);
}
