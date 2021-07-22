package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.InterestAreaDto;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.dao.MemberDao;

@Service("MemberServiceImpl")
public class MemberServiceImpl implements MemberService{

	@Autowired
	SqlSession session;
	
	@Override
	public boolean registerMember(MemberDto memberDto) {	
		return session.getMapper(MemberDao.class).registerMember(memberDto)==1;
	}

	@Override
	public MemberDto login(String userId, String userPwd) {
//		유효성 검사....
		MemberDto memberDto = new MemberDto();
		memberDto.setUserid(userId);
		memberDto.setUserpwd(userPwd);
		return session.getMapper(MemberDao.class).login(memberDto);
	}

	@Override
	public MemberDto getMember(String userId) {
		// TODO Auto-generated method stub
		return session.getMapper(MemberDao.class).getMember(userId);
	}

	@Override
	public boolean modifyMember(MemberDto memberDto) {
		return session.getMapper(MemberDao.class).modifyMember(memberDto)==1;

	}

	@Override
	public boolean deleteMember(String userId) {
		return session.getMapper(MemberDao.class).deleteMember(userId)==1;

	}

	@Override
	public boolean addInterestArea(InterestAreaDto interestAreaDto) {
		return session.getMapper(MemberDao.class).addInterestArea(interestAreaDto)==1;
		
	}

	@Override
	public String selInterestArea(String dong) {
	
		return session.getMapper(MemberDao.class).selInterestArea(dong);
	}

	@Override
	public List<InterestAreaDto> getInterestArea(String userid) {
		
		return session.getMapper(MemberDao.class).getInterestArea(userid);
	}

	@Override
	public boolean delteteInterestArea(InterestAreaDto interestAreaDto) {
		
		return session.getMapper(MemberDao.class).deleteInterestArea(interestAreaDto)==1;
	}
	

}
