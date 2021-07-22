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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.HouseSearchService;
import com.ssafy.happyhouse.model.service.HouseSearchServiceImpl;

@RestController
@RequestMapping("/housesearch")
@CrossOrigin("*")
public class HouseSearchController {
	private static final Logger logger = LoggerFactory.getLogger(HouseSearchController.class);
//	private static final long serialVersionUID = 1L;

	@Autowired
	private HouseSearchService hSer;
	
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String move() {
		return "housesearch";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public List<HouseInfoDto> listHouseInfo(){
		try {
			return hSer.listHouseInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/sido",method=RequestMethod.GET,headers = { "Content-type=application/json" })
	@ResponseBody
	public List<SidoGugunCodeDto> getSido(){
		try {
			return hSer.getSido();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/gugun",method=RequestMethod.GET,headers = { "Content-type=application/json" })
	public List<SidoGugunCodeDto> getGugunInSido(String sido){
		try {
			return hSer.getGugunInSido(sido);
		} catch (Exception e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/dong",method=RequestMethod.GET,headers = { "Content-type=application/json" })
	public List<HouseInfoDto> getDongInGugun(String gugun){
		try {
			return hSer.getDongInGugun(gugun);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping(value="/apt",method=RequestMethod.GET,headers = { "Content-type=application/json" })
	public List<HouseInfoDto> getAptInDong(String dong){
		try {
			return hSer.getAptInDong(dong);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/all",method=RequestMethod.GET,headers = { "Content-type=application/json" })
	public List<HouseInfoDto> getAll(){
		try {
			return hSer.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	

	@RequestMapping(value="/interest/{userid}",method=RequestMethod.GET)
	public List<HouseInfoDto> getInterest(@PathVariable("userid") String userId){
		try {
			return hSer.getInterest(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	

	@RequestMapping(value="/dealinterest/{userid}",method=RequestMethod.GET)
	public List<HouseDealDto> getDealInterest(@PathVariable("userid") String userId){
		try {
			return hSer.getDealInterest(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/aptofname/{aptname}",method=RequestMethod.GET)
	public List<HouseInfoDto> getAptOfName(@PathVariable("aptname") String aptName){
		try {
			return hSer.getAptOfName(aptName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/dealofaptname/{aptname}",method=RequestMethod.GET)
	public List<HouseDealDto> getDealOfAptName(@PathVariable("aptname") String aptName){
		try {
			return hSer.getDealOfAptName(aptName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
