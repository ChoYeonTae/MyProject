package com.sist.vo;
/*
 * 	데이터형 클래스 (사용자 정의 데이터형)
 * 	=>일기 (getter) ,쓰
 */
public class MemberVO {
	private String name; 
	private String sex;
	private String addr;
	private boolean email;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public boolean isEmail() {
		return email;
	}
	public void setEmail(boolean email) {
		this.email = email;
	}
	
	
	
}
