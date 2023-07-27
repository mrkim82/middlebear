package com.groo.bear.insa.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.emp.service.EmpVO;
import com.groo.bear.insa.mapper.InsaMapper;
import com.groo.bear.insa.service.InsaService;
import com.groo.bear.paging.Criteria;

@Service
public class InsaServiceImpl implements InsaService{
	@Autowired
	InsaMapper insaMapper;

	@Override
	public List<EmpVO> selectEmpList(Criteria cri, EmpVO vo) {
		return insaMapper.selectEmpList(cri, vo);
	}

	@Override
	public int EmpListCnt(Criteria cri, EmpVO vo) {
		return insaMapper.EmpListCnt(cri, vo);
	}

	@Override
	public EmpVO empDetailInfo(String id) {
		return insaMapper.empDetailInfo(id);
	}

	@Override
	public List<EmpVO> getDept() {
		return insaMapper.getDept();
	}

	@Override
	public int userInfoUpdate(EmpVO vo) {
		return insaMapper.userInfoUpdate(vo);
	}

	@Override
	public int usersUpdate(EmpVO vo) {
		return insaMapper.usersUpdate(vo);
	}

	@Override
	public int usersDelete(int empNo) {
		return insaMapper.usersDelete(empNo);
	}

	@Override
	public List<EmpVO> userInfoList(Criteria cri, EmpVO vo) {
		return insaMapper.userInfoList(cri, vo);
	}

	@Override
	public int userInfoListCnt(Criteria cri, EmpVO vo) {
		return insaMapper.userInfoListCnt(cri, vo);
	}

	@Override
	public int userInfoDel(List<EmpVO> vo) {
		int count = 0;
		for(int i=0; i < vo.size(); i++) {
			insaMapper.userInfoDel(vo.get(i));
			count += 1;
		}
		return count;
	}

	@Override
	public int userInfoAdd(EmpVO vo) {
		return insaMapper.userInfoAdd(vo);
	}

	@Override
	public int checkPno(String pno) {
		return insaMapper.checkPno(pno);
	}

	@Override
	public int updateUserInfo(EmpVO vo) {
		return insaMapper.updateUserInfo(vo);
	}
}
