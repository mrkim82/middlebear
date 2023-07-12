package com.groo.bear.mypage.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.emp.service.EmpVO;
import com.groo.bear.mypage.mapper.UserMapper;
import com.groo.bear.mypage.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;

	public EmpVO checkGrade(String id) {
		return userMapper.checkGrade(id);
	}

	
	
		
}	
