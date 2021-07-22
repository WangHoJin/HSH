package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;

public interface HouseSearchService {
	// 전체검색 (페이지 넘어가자마자 목록 보이게)
	List<HouseInfoDto> listHouseInfo() throws Exception;

	// 상세검색 (도/시 ,시/군/구, 동 선택 및 아파트별)
	List<HouseDealDto> listHouseDeal() throws Exception;

	List<SidoGugunCodeDto> getSido() throws Exception;

	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;

	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;

	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	
	List<HouseDealDto> getDealAptInDong(String dong,String aptName) throws Exception;

	List<HouseInfoDto> getAll() throws Exception;
	
	// 관심 지역 검색
	List<HouseInfoDto> getInterest(String userId) throws Exception;
	
	List<HouseDealDto> getDealInterest(String userId) throws Exception;

	List<HouseInfoDto> getAptOfName(String aptName) throws Exception;

	List<HouseDealDto> getDealOfAptName(String aptName) throws Exception;
}
