package com.groo.bear.mypage.mapper;

import java.util.List;
import java.util.Map;

import com.groo.bear.mypage.paging.Criteria;
import com.groo.bear.mypage.service.CarVO;

public interface CarMapper {

	//차량 전체조회
	public List<CarVO> selectAllCar();
	public List<CarVO> selectAllCar(CarVO carVO);
	//내차량 조회
	public List<CarVO> getMyCar(String id);	
	
	//페이징
	public List<Map<String, Object>> carList(Criteria cri) throws Exception;
	 
	public int carListCnt() throws Exception;

	
	
}
