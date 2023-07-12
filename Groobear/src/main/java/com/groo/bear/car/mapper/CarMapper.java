package com.groo.bear.car.mapper;

import java.util.List;

import com.groo.bear.car.service.CarVO;

public interface CarMapper {

	//차량 전체조회
	public List<CarVO> selectAllCar();
	//내차량 조회
	public List<CarVO> getMyCar(String id);	
	
	
	
	
}
