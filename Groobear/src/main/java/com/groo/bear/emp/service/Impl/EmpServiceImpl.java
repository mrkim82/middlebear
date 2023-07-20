package com.groo.bear.emp.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.emp.mapper.EmpMapper;
import com.groo.bear.emp.service.EmpService;
import com.groo.bear.emp.service.EmpVO;

@Service
public class EmpServiceImpl implements EmpService {
	
	@Autowired
	EmpMapper empMapper;
	
	@Override
	public EmpVO getEmpInfo(EmpVO vo) {	
		return empMapper.getEmpInfo(vo);
	}

	@Override
	public int changePw(EmpVO vo) {
		return empMapper.changePw(vo);
	}

	@Override
	public List<EmpVO> searchEmpInfo(String name) {
		return empMapper.searchEmpInfo(name);
	}

	@Override
	public int checkId(String id) {
		return empMapper.checkId(id);
	}

	@Override
	public int checkPhone(String phone) {
		return empMapper.checkPhone(phone);
	}

	@Override
	public int signUp(EmpVO vo) {
		return empMapper.signUp(vo);
	}

}
