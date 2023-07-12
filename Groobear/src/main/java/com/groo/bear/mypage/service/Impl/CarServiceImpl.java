package com.groo.bear.mypage.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.mypage.mapper.CarMapper;
import com.groo.bear.mypage.service.CarService;
import com.groo.bear.mypage.service.CarVO;

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	CarMapper carMapper;

	@Override
	public List<CarVO> getAllCarList() {
		return carMapper.selectAllCar();
	}

	@Override
	public List<CarVO> getMyCarList(String id) {
		return carMapper.getMyCar(id);
	}
	
	
	
		
}	
