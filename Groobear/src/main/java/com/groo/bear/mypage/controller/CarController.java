package com.groo.bear.mypage.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.groo.bear.mypage.service.CarService;
import com.groo.bear.mypage.service.CarVO;
import com.groo.bear.mypage.service.UserService;
import com.groo.bear.paging.Criteria;
import com.groo.bear.paging.Paging;

@Controller
public class CarController {

	@Autowired
	CarService carService;
	
	@Autowired
	UserService userService;
	
	
	//개인 차량 페이지 조회
	@GetMapping("/carList")
	public String getCarList(HttpServletRequest request, Model model, CarVO carVO, Criteria cri) {
		int carListCnt = carService.carCnt(cri,carVO);
			
		Paging paging = new Paging();
		paging.setCri(cri);
		paging.setTotalCount(carListCnt);    
		
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("Id");
		String empG = (String)session.getAttribute("EmpGrade");
        
		if(empG.equals("A")) {
			model.addAttribute("carList",carService.getAllCarList(cri, carVO));
		    model.addAttribute("paging",paging);
			return "car/carList";
			
		}else {
			model.addAttribute("info",carService.getMyCarInfo(id));
			model.addAttribute("carPList",carService.getMyCarList(id));
			return "car/carPList";
		}
		
	}
	
	
	//차량 중복 체크 
	@ResponseBody
	@PostMapping("carChk")
	public String carChk(@RequestBody CarVO carVO,String carNo) {
		String result = "";
		if(carService.carNoChk(carVO.getCarNo()) > 0) {
			result = "true";
		}else {
			result = "false";
		}
		return result;
	}
	

	//개인차량 등록 
	@ResponseBody
	@PostMapping("carInsert")
	public CarVO addCar(Model model, @RequestBody CarVO carVO, String carNo) {
			if(carService.addCar(carVO)>0) {
				return carVO;
			} else {
				return null;
			}
	}
	
	
	//차량수정 
	@ResponseBody
	@PostMapping("carUpdate")
	public CarVO carUpdate(Model model,@RequestBody CarVO carVO,String carNo) {
		if(carService.carUpdate(carVO)>0) {
			return carVO;
		} else {
			return null;
		}
	}
	
	//삭제
	@ResponseBody
	@PostMapping("carDelete")
	public String carDelete(@RequestBody CarVO carVO) {
		String result = "";
		if(carService.carDelete(carVO.getCarNo())>0) {
			result = "true";
		} else {
			result = "false";
		}
		
		return result;
	}
	
	//운행일지 조회
	@GetMapping("bookList/{monthDate}")
	public String carBook(HttpServletRequest request, Model model, CarVO carVO, Criteria cri,@PathVariable String monthDate) {
		
		int bookListCnt = carService.bookCnt(cri, carVO);
			
		Paging paging = new Paging();
		paging.setCri(cri);
		paging.setTotalCount(bookListCnt);    
		
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("Id");
		String empG = (String)session.getAttribute("EmpGrade");
		
		Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
	    String fd = sdf.format(date);
	    
		
		if(empG.equals("A")) {
			model.addAttribute("bookList",carService.allBook(cri, carVO));
			model.addAttribute("paging",paging);
			
			return "car/bookA";
		}else {
			model.addAttribute("bookList",carService.getBook(cri,id, monthDate));
			model.addAttribute("paging",paging);
			return "car/bookP";
		}
	
	
	}
	

	
	
}
