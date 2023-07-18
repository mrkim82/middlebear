package com.groo.bear.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.com.mapper.ComMapper;

@Service
public class ComServiceImpl implements ComService{
	
	@Autowired
	ComMapper comMapper;

	@Override
	public List<ComVO> comAllList() {
		return comMapper.comAllList();
	}
	
	
}
