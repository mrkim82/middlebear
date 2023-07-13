package com.groo.bear.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.groo.bear.emp.service.EmpVO;
import com.groo.bear.security.mapper.SecurityMapper;
import com.groo.bear.security.service.SecurityService;
import com.groo.bear.security.service.SecurityVO;

@Service
public class SecurityServiceImpl implements SecurityService, UserDetailsService {
	
	@Autowired
	SecurityMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SecurityVO vo = userMapper.secLogin(username);
		
		if(vo == null) {
			throw new UsernameNotFoundException("no user");
		}
		return vo;
	}

	@Override
	public SecurityVO secLogin(String id) {
		return userMapper.secLogin(id);
	}

}
