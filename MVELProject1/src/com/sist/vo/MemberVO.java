package com.sist.vo;
/*
 * 	�������� Ŭ���� (����� ���� ��������)
 * 	=>�ϱ� (getter) ,��
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
