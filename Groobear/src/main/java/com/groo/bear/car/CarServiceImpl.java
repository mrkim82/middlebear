package com.groo.bear.car;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


public class CarServiceImpl implements CarService {
	
	@Autowired
	CarMapper carMapper;

	@Override
	public List<CarVO> getCarList() {
		return carMapper.selectAllCar();
	}
	
	
	
		
}
