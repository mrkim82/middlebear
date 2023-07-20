package com.groo.bear.mypage.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.mypage.mapper.CommuteMapper;
import com.groo.bear.mypage.service.CarVO;
import com.groo.bear.mypage.service.CommuteService;
import com.groo.bear.mypage.service.CommuteVO;
import com.groo.bear.paging.Criteria;

@Service
public class CommuteServiceImpl implements CommuteService {
	
	@Autowired
	CommuteMapper commuteMapper;



	@Override
	public List<CommuteVO> getAllCommuteList(Criteria cri, CommuteVO commuteVO) {
		return commuteMapper.selectAllCommute(cri, commuteVO);
	}


	@Override
	public int commuteCnt(Criteria cri, CommuteVO commuteVO) {
		return commuteMapper.commuteListCnt(cri, commuteVO);
	}





//	@Override
//	public List<CarVO> getAllCarList(Criteria cri, CarVO carVO) {
//		return carMapper.selectAllCar(cri, carVO);
//	}
//
//	@Override
//	public List<CarVO> getMyCarInfo(String id) {
//		return carMapper.getCarInfo(id);
//	}
//	
//	@Override
//	public int carCnt(Criteria cri, CarVO carVO){
//		return carMapper.carListCnt(cri, carVO);
//	}
//
//	@Override
//	public int addCar(CarVO carVO) {
//		return carMapper.insertCar(carVO);
//	}
//
//	@Override
//	public int carUpdate(CarVO carVO) {
//		return carMapper.updateCar(carVO);
//	}
//
//	@Override
//	public int carDelete(String carNo) {
//		return carMapper.deleteCar(carNo);
//	}
//
//	

	
	
	
	

	
		
}	
