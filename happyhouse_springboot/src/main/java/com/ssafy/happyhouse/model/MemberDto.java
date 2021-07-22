package com.ssafy.happyhouse.model;

public class MemberDto {

	private String userid;
	private String username;
	private String userpwd;
	private String address;
	private String phone;

	public MemberDto() {

	}

	public MemberDto(String userid, String username, String userpwd, String address, String phone) {
		super();
		this.userid = userid;
		this.username = username;
		this.userpwd = userpwd;
		this.address = address;
		this.phone = phone;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "MemberDto [userid=" + userid + ", username=" + username + ", userpwd=" + userpwd + ", address="
				+ address + ", phone=" + phone + "]";
	}

}
