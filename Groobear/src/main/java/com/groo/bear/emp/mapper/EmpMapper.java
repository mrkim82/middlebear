package com.groo.bear.emp.mapper;

import com.groo.bear.emp.service.EmpVO;

public interface EmpMapper {
	//사원정보조회
	public EmpVO getEmpInfo(EmpVO vo);
	public int changePw(EmpVO vo);
}
