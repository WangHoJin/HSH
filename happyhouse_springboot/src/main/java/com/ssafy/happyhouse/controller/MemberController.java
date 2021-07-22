package com.ssafy.happyhouse.controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.model.InterestAreaDto;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.service.MemberService;
import com.ssafy.happyhouse.model.service.MemberServiceImpl;

@RestController
@RequestMapping("/member")
@CrossOrigin("*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/mvsearch", method = RequestMethod.GET)	
	public String home(Locale locale, Model model) {
		return "housesearch";
	}

	@RequestMapping(value="/select/{userid}",method=RequestMethod.GET)
	public MemberDto selectMember(@PathVariable("userid") String userid){
		System.out.println("sel");
		return memberService.getMember(userid);
	}
	
	@RequestMapping(value="/getareainterest/{userid}",method=RequestMethod.GET)
	public List<InterestAreaDto> getAreaInterest(@PathVariable("userid") String userid){
		return memberService.getInterestArea(userid);
	}
	
	@RequestMapping(value="/deleteinterest/{userid}/{dongcode}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteInterest(@PathVariable("userid") String userid,@PathVariable("dongcode") String dongcode){
		InterestAreaDto interestAreaDto=new InterestAreaDto(userid,dongcode);
		System.out.println(userid+" "+dongcode);
		if(memberService.delteteInterestArea(interestAreaDto)) {
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
	}
		
	@RequestMapping(value = "/addinterest", method = RequestMethod.POST,headers = { "Content-type=application/json" })
    public ResponseEntity<String> addInterestArea(@RequestBody Map<String, String> map) {
        String userid=map.get("userid");
        String dong=map.get("dong");
        String dongcode = memberService.selInterestArea(dong);
        System.out.println(userid+" "+dong+" "+dongcode);
        InterestAreaDto interestAreaDto = new InterestAreaDto(userid, dongcode);
        if(memberService.addInterestArea(interestAreaDto)) {
            return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
    }

	@RequestMapping(value = "/delete/{userid}", method =RequestMethod.DELETE)
	public ResponseEntity<String> deleteMember(@PathVariable("userid") String userid) {
		if(memberService.deleteMember(userid)) {
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/modify", method = RequestMethod.PUT)
	public ResponseEntity<String> modify(@RequestBody MemberDto memberDto) {
		
		if(memberService.modifyMember(memberDto)) {
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody MemberDto memberDto) throws Exception {
		System.out.println("reigist");
		System.out.println(memberDto.toString());
		if(memberService.registerMember(memberDto)) {
			System.out.println("성공이낙여");
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<String> loginMember(@RequestBody Map<String, String> map, HttpServletRequest req, ModelAndView mv) {
		System.out.println("login");
		String userid = map.get("userid");
		String userpwd = map.get("userpwd");

		MemberDto memberDto = memberService.login(userid, userpwd);
		if (memberDto != null) { // 성공
//			session 설정
			HttpSession session = req.getSession();
			session.setAttribute("userinfo", memberDto);
			System.out.println("성공");
			return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
		} else { // 실패
			mv.addObject("msg", "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
			System.out.println("실패");
			return new ResponseEntity<String>(FAIL,HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}