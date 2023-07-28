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
		System.out.println("111111");
		System.out.println(cri);
		int carListCnt = carService.carCnt(cri,carVO);
		System.out.println(carListCnt);
			
		Paging paging = new Paging();
		paging.setCri(cri);
		paging.setTotalCount(carListCnt);    
		
		System.out.println(paging);

		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("Id");
		String empG = (String)session.getAttribute("EmpGrade");
		System.out.println(empG);
		//String empG = userService.checkGrade(id).getEmpGrade();
        
		if(empG.equals("A")) {
			model.addAttribute("carList",carService.getAllCarList(cri, carVO));
		    model.addAttribute("paging",paging);
		    System.out.println(model);
			return "car/carList";
			
		}else {
			model.addAttribute("info",carService.getMyCarInfo(id));
			System.out.println(model);
			model.addAttribute("carPList",carService.getMyCarList(id));
			System.out.println(model);
			return "car/carPList";
		}
		
	}
	

	//개인차량 등록 
	
	@PostMapping("carInsert")
	public String addCar(Model model, CarVO carVO) {
		carService.addCar(carVO);
		return "redirect:carList";
	}
	
	
	//차량수정 
	@PostMapping("carUpdate")
	public String carUpdate(Model model, CarVO carVO) {
		carService.carUpdate(carVO);
		return "redirect:carList";
	}
	
	//삭제
	@ResponseBody
	@PostMapping("carDelete")
	public String carDelete(@RequestBody CarVO carVO) {
		System.out.println(carVO.getCarNo());
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
		System.out.println(bookListCnt);
			
		Paging paging = new Paging();
		paging.setCri(cri);
		paging.setTotalCount(bookListCnt);    
		
		System.out.println(paging);

		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("Id");
		String empG = (String)session.getAttribute("EmpGrade");
		System.out.println(empG);
		
		Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
	    String fd = sdf.format(date);
	    
		
		if(empG.equals("A")) {
			model.addAttribute("bookList",carService.allBook(cri, carVO));
			model.addAttribute("paging",paging);
			System.out.println(model);
			
			return "car/bookA";
		}else {
			model.addAttribute("bookList",carService.getBook(cri,id, monthDate));
			model.addAttribute("paging",paging);
			System.out.println(model);
			return "car/bookP";
		}
	
	
	}
	

	
	
}
