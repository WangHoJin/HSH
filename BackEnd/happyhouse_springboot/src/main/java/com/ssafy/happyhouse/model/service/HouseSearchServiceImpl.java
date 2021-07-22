package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.dao.HouseSearchDao;

@Service
public class HouseSearchServiceImpl implements HouseSearchService {

	private static final Logger logger = LoggerFactory.getLogger(HouseSearchServiceImpl.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public List<HouseInfoDto> listHouseInfo() throws Exception {
		
		return sqlSession.getMapper(HouseSearchDao.class).listHouseInfo();
	}

	@Override
	public List<HouseDealDto> listHouseDeal() throws Exception {
		return sqlSession.getMapper(HouseSearchDao.class).listHouseDeal();
	}
	
	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return sqlSession.getMapper(HouseSearchDao.class).getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return sqlSession.getMapper(HouseSearchDao.class).getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(HouseSearchDao.class).getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return sqlSession.getMapper(HouseSearchDao.class).getAptInDong(dong);
	}
	
	@Override
	public List<HouseInfoDto> getAll() throws Exception {
		return sqlSession.getMapper(HouseSearchDao.class).getAll();
	}

	@Override
	public List<HouseInfoDto> getAptOfName(String aptName) throws Exception {
		return sqlSession.getMapper(HouseSearchDao.class).getAptOfName(aptName);
	}
	
	@Override
	public List<HouseDealDto> getDealAptInDong(String dong, String aptName) throws Exception {
		HouseDealDto houseDealDto = new HouseDealDto();
		houseDealDto.setDong(dong);
		houseDealDto.setAptName(aptName);
		return sqlSession.getMapper(HouseSearchDao.class).getDealAptInDong(houseDealDto);
	}

	@Override
	public List<HouseDealDto> getDealOfAptName(String aptName) throws Exception {
		return sqlSession.getMapper(HouseSearchDao.class).getDealOfAptName(aptName);
	}

	@Override
	public List<HouseInfoDto> getInterest(String userId) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(HouseSearchDao.class).getInterest(userId);
	}

	@Override
	public List<HouseDealDto> getDealInterest(String userId) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(HouseSearchDao.class).getDealInterest(userId);
	}

	

}