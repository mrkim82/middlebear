package com.groo.bear.mypage.service;

import java.util.List;

import com.groo.bear.paging.Criteria;

public interface CarService {

	//전체 조회
	//public List<CarVO> getAllCarList();
	public List<CarVO> getAllCarList(Criteria cri, CarVO carVO);
	//페이징
	public int carCnt(Criteria cri, CarVO carVO);
	
	//차량 개별조회 
	public List<CarVO> getMyCarList(String id);
	public List<CarVO> getMyCarInfo(String id);
	
	
	//추가
	public int addCar(CarVO carVO);
	//수정 
	public int carUpdate(CarVO carVO);
	//삭제
	public int carDelete(String carNo);
	
	//운행일지 전체 조회
	public List<CarVO> allBook(Criteria cri, CarVO carVO);
	//운행일지 페이징
	public int bookCnt(Criteria cri, CarVO carVO);
	//개인 조회 
	public List<CarVO> getBook(Criteria cri, String id,String monthDate);
	//차량 번호 중복 체크
	public int carNoChk(String carNo);
	
	
}
