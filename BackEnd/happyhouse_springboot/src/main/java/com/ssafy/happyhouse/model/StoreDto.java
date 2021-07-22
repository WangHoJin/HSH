package com.ssafy.happyhouse.model;

public class StoreDto {
	private int sno;
	private String sname;
	private String branchname;
	private String sido_code;
	private String city;
	private String gugun_code;
	private String gugun_name;
	private String dong;
	private String address1;
	private String address2;
	private String lat;
	private String lng;
	private String sengname;
	
	private int cnt;
	private int storecnt;
	private int no;
	private double radius;
	private double distance;
	
	public StoreDto() {
		super();
	}
	
	

	public StoreDto(int sno, String sname, String branchname, String sido_code, String city, String gugun_code,
			String gugun_name, String dong, String address1, String address2, String lat, String lng, String sengname,
			int cnt, int storecnt) {
		super();
		this.sno = sno;
		this.sname = sname;
		this.branchname = branchname;
		this.sido_code = sido_code;
		this.city = city;
		this.gugun_code = gugun_code;
		this.gugun_name = gugun_name;
		this.dong = dong;
		this.address1 = address1;
		this.address2 = address2;
		this.lat = lat;
		this.lng = lng;
		this.sengname = sengname;
		this.cnt = cnt;
		this.storecnt = storecnt;
	}

	public StoreDto(String lat,String lng) {
		super();
		this.lat=lat;
		this.lng=lng;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getBranchname() {
		return branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}

	public String getSido_code() {
		return sido_code;
	}

	public void setSido_code(String sido_code) {
		this.sido_code = sido_code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGugun_code() {
		return gugun_code;
	}

	public void setGugun_code(String gugun_code) {
		this.gugun_code = gugun_code;
	}

	public String getGugun_name() {
		return gugun_name;
	}

	public void setGugun_name(String gugun_name) {
		this.gugun_name = gugun_name;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getSengname() {
		return sengname;
	}

	public void setSengname(String sengname) {
		this.sengname = sengname;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getStorecnt() {
		return storecnt;
	}

	public void setStorecnt(int storecnt) {
		this.storecnt = storecnt;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}



	@Override
	public String toString() {
		return "StoreDto [sno=" + sno + ", sname=" + sname + ", branchname=" + branchname + ", sido_code=" + sido_code
				+ ", city=" + city + ", gugun_code=" + gugun_code + ", gugun_name=" + gugun_name + ", dong=" + dong
				+ ", address1=" + address1 + ", address2=" + address2 + ", lat=" + lat + ", lng=" + lng + ", sengname="
				+ sengname + ", cnt=" + cnt + ", storecnt=" + storecnt + ", aptName=" + no + ", radius=" + radius
				+ ", distance=" + distance + "]";
	}
	
	
	
	
}
