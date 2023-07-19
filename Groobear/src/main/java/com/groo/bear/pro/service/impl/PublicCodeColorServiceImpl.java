package com.groo.bear.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.pro.mapper.PublicCodeColorMapper;
import com.groo.bear.pro.service.ProGroupVO;
import com.groo.bear.pro.service.PublicCodeColorService;
import com.groo.bear.pro.service.PublicCodeColorVO;

@Service
public class PublicCodeColorServiceImpl implements PublicCodeColorService {
	
	@Autowired
	PublicCodeColorMapper pccm;
	
	@Override
	public List<PublicCodeColorVO> readPublicCodeColorAll() {
		return pccm.readPublicCodeColorAll();
	}

	@Override
	public int updateProMemColor(ProGroupVO vo) {
		return pccm.updateProMemColor(vo);
	}

}
