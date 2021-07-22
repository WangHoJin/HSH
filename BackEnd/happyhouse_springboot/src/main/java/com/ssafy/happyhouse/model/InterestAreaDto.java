package com.ssafy.happyhouse.model;

public class InterestAreaDto {
	private String userid;
	private String dongcode;
	private String dong;
	private String city;
	private String gugun;

	public InterestAreaDto() {
	}

	public InterestAreaDto(String userid, String dongcode) {
		super();
		this.userid = userid;
		this.dongcode = dongcode;
	}
	
	public InterestAreaDto(String dong, String city, String gugun) {
		super();
		this.dong=dong;
		this.city=city;
		this.gugun=gugun;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDongcode() {
		return dongcode;
	}

	public void setDongcode(String dongcode) {
		this.dongcode = dongcode;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGugun() {
		return gugun;
	}

	public void setGugun(String gugun) {
		this.gugun = gugun;
	}

	@Override
	public String toString() {
		return "InterestAreaDto [userid=" + userid + ", dongcode=" + dongcode + "]";
	}

}
