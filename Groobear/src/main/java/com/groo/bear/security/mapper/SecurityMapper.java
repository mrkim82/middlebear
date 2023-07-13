package com.groo.bear.security.mapper;

import com.groo.bear.security.service.SecurityVO;

public interface SecurityMapper {
	//로그인
	public SecurityVO secLogin(String id);
}
