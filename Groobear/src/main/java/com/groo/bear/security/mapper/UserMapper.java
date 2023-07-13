package com.groo.bear.security.mapper;

import com.groo.bear.security.service.UserVO;

public interface UserMapper {
	//로그인
	public UserVO secLogin(String id);
}
