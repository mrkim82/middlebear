package com.groo.bear.mypage.service;

import java.util.List;

import com.groo.bear.paging.Criteria;

public interface CommuteService {

	//전체 조회
	public List<CommuteVO> getAllCommuteList(Criteria cri, CommuteVO commuteVO);
	//페이징
	public int commuteCnt(Criteria cri, CommuteVO commuteVO);
//	//차량 개별조회 
//	public List<CarVO> getMyCarList(String id);
//	public List<CarVO> getMyCarInfo(String id);
//	

//	//추가
//	public int addCar(CarVO carVO);
//	//수정 
//	public int carUpdate(CarVO carVO);
//	//삭제
//	public int carDelete(String carNo);




}
