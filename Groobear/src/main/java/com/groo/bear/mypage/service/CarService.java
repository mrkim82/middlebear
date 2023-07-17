package com.groo.bear.mypage.service;

import java.util.List;

import com.groo.bear.paging.Criteria;

public interface CarService {

	//전체 조회
	//public List<CarVO> getAllCarList();
	public List<CarVO> getAllCarList(Criteria cri, CarVO carVO);
	//차량 개별조회 
	public List<CarVO> getMyCarList(String id);
	public List<CarVO> getMyCarInfo(String id);
	
	//페이징
	public int carCnt(Criteria cri, CarVO carVO);
	//추가
	public int addCar(CarVO carVO);
	//수정 
	public int carUpdate(CarVO carVO);
	//삭제
	public int carDelete(String carNo);




}
