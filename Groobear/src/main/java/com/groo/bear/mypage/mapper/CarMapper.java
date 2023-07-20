package com.groo.bear.mypage.mapper;

import java.util.List;

import com.groo.bear.mypage.service.CarVO;
import com.groo.bear.paging.Criteria;

public interface CarMapper {

	//차량 전체조회
	public List<CarVO> selectAllCar(Criteria cri,CarVO carVO);
	//페이징
	public int carListCnt(Criteria cri, CarVO carVO);
	
	//내차량 조회
	public List<CarVO> getMyCar(String id);	
	public List<CarVO> getCarInfo(String id);
	
	

	//추가
	public int insertCar(CarVO carVO); 
	//수정 
	public int updateCar(CarVO carVO);
	//삭제
	public int deleteCar(String carNo);
	
	
	
	
	
}
