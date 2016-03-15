package com.ktds.jgbaek.member.vo;

public class MemberVO {
	
	private String memberId;
	private String nickName;
	private String password;
	private String email;
	private String isAdmin;
	
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String string) {
		this.memberId = string;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isAdmin(){
		if ( this.isAdmin != null && this.isAdmin.equals("Y")){
			return true;
		}
		else{
			return false;
		}
		
	}

}
