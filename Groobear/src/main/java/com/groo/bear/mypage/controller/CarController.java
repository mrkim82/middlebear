package com.groo.bear.mypage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	//전체조회 페이지
//	@GetMapping("carList")
//	public String carList(Model model) {
//		model.addAttribute("carList",carService.getAllCarList());
//		return "car/carList";
//	}
	
	//개인 차량 페이지 조회
	@GetMapping("carList")
	public String carList(HttpServletRequest request, Model model, Criteria cri, Pageable pageable)throws Exception {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("Id");
		System.out.println(id);
		String empG = userService.checkGrade(id).getEmpGrade();
		System.out.println(empG);
//		
//		int carListCnt = carService.carListCnt();
//		
//		Paging paging = new Paging();
//		paging.setCri(cri);
//        paging.setTotalCount(carListCnt);    
//        System.out.println(paging);
//        System.out.println(paging.getCri());
        
        
		if(empG.equals("A")) {
			
			model.addAttribute("carList",carService.getAllCarList());
		    
		        System.out.println(carService.getAllCarList());
			return "car/carList";
		}else {
			model.addAttribute("carPList",carService.getMyCarList(id));
			return "car/carPList";
		}
		
	}
	//검색
	@PostMapping("searchPList")
	   @ResponseBody
	   public List<CarVO> searchList(@RequestBody CarVO carVO) {
	      System.out.println(carVO);
	      return carService.getAllCarList(carVO);
	   }
	
//	@RequestMapping("carList")
//	public String carList(Criteria cri, Model model) throws Exception{
//		
//		int carListCnt = carService.carListCnt();
//	        
//	        // 페이징 객체
//	        Paging paging = new Paging();
//	        paging.setCri(cri);
//	        paging.setTotalCount(carListCnt);    
//	        
//	        List<Map<String, Object>> list = carService.carList(cri);
//	        
//	        model.addAttribute("list", list);
//	        System.out.println(list);
//	        model.addAttribute("paging", paging);    
//	        System.out.println(paging);
//		
//		return "carList";
//	}
	
	//개인차량 등록 
	
	
	
	
	
	
	
	
	
	
	
	
}
