package com.groo.bear.mypage.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.mypage.mapper.CarMapper;
import com.groo.bear.mypage.service.CarService;
import com.groo.bear.mypage.service.CarVO;
import com.groo.bear.paging.Criteria;

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	CarMapper carMapper;


	@Override
	public List<CarVO> getMyCarList(String id) {
		return carMapper.getMyCar(id);
	}

	@Override
	public List<CarVO> getAllCarList(Criteria cri, CarVO carVO) {
		return carMapper.selectAllCar(cri, carVO);
	}

	@Override
	public List<CarVO> getMyCarInfo(String id) {
		return carMapper.getCarInfo(id);
	}
	
	@Override
	public int carCnt(Criteria cri, CarVO carVO){
		return carMapper.carListCnt(cri, carVO);
	}

	@Override
	public int addCar(CarVO carVO) {
		return carMapper.insertCar(carVO);
	}

	@Override
	public int carUpdate(CarVO carVO) {
		return carMapper.updateCar(carVO);
	}

	@Override
	public int carDelete(String carNo) {
		return carMapper.deleteCar(carNo);
	}

	

	
	
	
	

	
		
}	
