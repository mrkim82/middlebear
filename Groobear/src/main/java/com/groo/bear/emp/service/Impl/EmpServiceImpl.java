package com.groo.bear.emp.service.Impl;

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

}
