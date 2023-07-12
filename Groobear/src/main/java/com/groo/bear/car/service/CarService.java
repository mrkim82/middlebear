package com.groo.bear.car.service;

import java.util.List;

public interface CarService {

	//전체 조회
	public List<CarVO> getAllCarList();
	//차량 개별조
	public List<CarVO> getMyCarList(String id);
	
	
	
}
