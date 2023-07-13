package com.groo.bear.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.groo.bear.emp.service.EmpVO;
import com.groo.bear.security.mapper.UserMapper;
import com.groo.bear.security.service.UserService;
import com.groo.bear.security.service.UserVO;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	
	@Autowired
	UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO vo = userMapper.secLogin(username);
		
		if(vo == null) {
			throw new UsernameNotFoundException("no user");
		}
		return vo;
	}

	@Override
	public UserVO secLogin(String id) {
		return userMapper.secLogin(id);
	}

}
