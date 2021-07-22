package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.CoffeeShopDto;

public interface CoffeeShopService {
		// 전체 카페 검색
		List<CoffeeShopDto> listCoffeeShopInfo();
		
		// 해당 동 카페 검색
		List<CoffeeShopDto> getCoffeeShopInDong(String dong);
		
		// 반경 내 카페 정보
		List<CoffeeShopDto> getCoffeeShopRadius(CoffeeShopDto coffeeShopDto);
		
		// 반경 내 카페 랭크
		List<CoffeeShopDto> getCoffeeShopRadiusRank(CoffeeShopDto coffeeShopDto);
		
		// 커피집이 가장 많은 동네
		List<CoffeeShopDto> getDongRank(String gugun_name);
		
		// 해당 동 내 커피샵 순위
		List<CoffeeShopDto> getCoffeeShopRank(String dong);
		
		// 마커클릭시 카페 정보 가져오기
		List<CoffeeShopDto> getCoffeeMarkerInfo(CoffeeShopDto coffeeShopDto);
}
