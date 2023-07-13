package com.groo.bear.mypage.service;

import java.util.List;
import java.util.Map;

import com.groo.bear.mypage.paging.Criteria;

public interface CarService {

	//전체 조회
	public List<CarVO> getAllCarList();
	public List<CarVO> getAllCarList(CarVO carVO);
	//차량 개별조
	public List<CarVO> getMyCarList(String id);
	
	//페이징
	public int carListCnt() throws Exception;
	public List<Map<String, Object>> carList(Criteria cri) throws Exception;
	
}
