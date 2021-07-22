package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.HouseSearchService;
import com.ssafy.happyhouse.model.service.HouseSearchServiceImpl;

@RestController
@RequestMapping("/housesearch2")
@CrossOrigin("*")
public class HouseSearchController2 {
	private static final Logger logger = LoggerFactory.getLogger(HouseSearchController2.class);
//	private static final long serialVersionUID = 1L;

	@Autowired
	private HouseSearchService hSer;
	
	@GetMapping(value = "/houseall")
	public ResponseEntity<List<HouseInfoDto>> listHouseInfo() throws Exception {
		List<HouseInfoDto> list = hSer.listHouseInfo();
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/house")
	public ResponseEntity<List<SidoGugunCodeDto>> getSido() throws Exception {
		List<SidoGugunCodeDto> list = hSer.getSido();
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<SidoGugunCodeDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/house/{sido}")
	public ResponseEntity<List<SidoGugunCodeDto>> getGugunInSido(@PathVariable("sido") String sido) throws Exception {
		List<SidoGugunCodeDto> list = hSer.getGugunInSido(sido);
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<SidoGugunCodeDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/house/{sido}/{gugun}")
	public ResponseEntity<List<HouseInfoDto>> getDongInGugun(@PathVariable("sido") String sido, @PathVariable("gugun") String gugun) throws Exception {
		List<HouseInfoDto> list = hSer.getDongInGugun(gugun);
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/house/{sido}/{gugun}/{dong}")
	public ResponseEntity<List<HouseInfoDto>> getAptInDong(@PathVariable("dong") String dong) throws Exception {
		List<HouseInfoDto> list = hSer.getAptInDong(dong);
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/housedeal/{dong}/{aptName}")
	public ResponseEntity<List<HouseDealDto>> getDealAptInDong(@PathVariable("dong") String dong,@PathVariable("aptName") String aptName) throws Exception {
		List<HouseDealDto> list = hSer.getDealAptInDong(dong, aptName);
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<HouseDealDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/interest/{userid}")
	public ResponseEntity<List<HouseInfoDto>> getInterest(@PathVariable("userid") String userid) throws Exception {
		List<HouseInfoDto> list = hSer.getInterest(userid);
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/dealinterest/{userid}")
	public ResponseEntity<List<HouseDealDto>> getDealinterest(@PathVariable("userid") String userid) throws Exception {
		List<HouseDealDto> list = hSer.getDealInterest(userid);
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<HouseDealDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
}
