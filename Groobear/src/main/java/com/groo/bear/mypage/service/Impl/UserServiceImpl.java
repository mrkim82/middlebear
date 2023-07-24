package com.groo.bear.mypage.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.emp.service.EmpVO;
import com.groo.bear.files.mapper.FilesMapper;
import com.groo.bear.mypage.mapper.UserMapper;
import com.groo.bear.mypage.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	FilesMapper filesMapper;

	public EmpVO checkGrade(String id) {
		return userMapper.checkGrade(id);
	}

	@Override
	public EmpVO checkMypage(EmpVO vo) {
		return userMapper.checkMypage(vo);
	}

	@Override
	public EmpVO checkScPw(String id) {
		return userMapper.checkScPw(id);
	}

	@Override
	public int insertProfImg(EmpVO vo) {
		return filesMapper.insertProfImg(vo);
	}

	@Override
	public int deleteProfImg(String id) {
		return filesMapper.deleteProfImg(id);
	}

	@Override
	public int updateUsers(EmpVO vo) {
		return userMapper.updateUsers(vo);
	}

	@Override
	public EmpVO checkMyProf(String id) {
		return userMapper.checkMyProf(id);
	}
	
	
	
	
		
}	
