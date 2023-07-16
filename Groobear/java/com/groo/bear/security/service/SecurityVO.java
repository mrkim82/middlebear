package com.groo.bear.security.service;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class SecurityVO implements UserDetails{
	
	private int empNo;
	private String name;
	private String rank;
	private String pno;
	private Date pinfoDate;
	private int deptNo;
	private String deptName;
	private String id;
	private String password;
	private String email;
	private int phone;
	private String addr;
	//List<String> empGrade;
	private String empGrade;
	private Date empDate;
	private String sign;
	private String profileImg;
	private String empStatus;
	private String profileNote;
	
	/*
	 * @Override public Collection<? extends GrantedAuthority> getAuthorities() {
	 * Collection<SimpleGrantedAuthority> roles = new
	 * ArrayList<SimpleGrantedAuthority>(); for(String role : empGrade) {
	 * roles.add(new SimpleGrantedAuthority(role)); } return roles; }
	 */
	@Override
	public String getUsername() {
		return id;
	}
	@Override
	public boolean isAccountNonExpired() {	//휴먼, 패스워드
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {	// 5회 비번 입력 에러
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
}