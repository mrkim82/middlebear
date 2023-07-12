package com.groo.bear.pro.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.groo.bear.pro.service.ProService;
import com.groo.bear.pro.service.ProVO;

@Controller
public class ProController {
	
	@Autowired
	ProService proService;
	
	//공통 데이터 전달
	private Model proData(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		model.addAttribute("projectGroupList", proService.readProjectGroup((String)session.getAttribute("Id")));
		model.addAttribute("projectPartiList", proService.readProjectParti((String)session.getAttribute("Id")));
		
		return model;
	}
	
	//프로젝트 메인 페이지 이동
	@GetMapping("proMain")
	public String proMainPage(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		model.addAttribute("projectMainList", proService.readProject((String)session.getAttribute("Id")));
//		model.addAttribute("projectGroupList", proService.readProjectGroup((String)session.getAttribute("Id")));
//		model.addAttribute("projectPartiList", proService.readProjectParti((String)session.getAttribute("Id")));
		proData(model, request);
		
		return "proHome/proMain";
	}
	
	//프로젝트 메인 페이지 즐찾 보기
	@GetMapping("proMainS")
	public String proMainPageS(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		model.addAttribute("projectMainList", proService.readProjectStar((String)session.getAttribute("Id")));
//		model.addAttribute("projectGroupList", proService.readProjectGroup((String)session.getAttribute("Id")));
//		model.addAttribute("projectPartiList", proService.readProjectParti((String)session.getAttribute("Id")));
		proData(model, request);
		return "proHome/proMainStar";
	}
	
	//프로젝트 메인 페이지 숨김 보기
	@GetMapping("proMainH")
	public String proMainPageH(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		model.addAttribute("projectMainList", proService.readProjectHide((String)session.getAttribute("Id")));
//		model.addAttribute("projectGroupList", proService.readProjectGroup((String)session.getAttribute("Id")));
//		model.addAttribute("projectPartiList", proService.readProjectParti((String)session.getAttribute("Id")));
		proData(model, request);
		System.out.println(model);
		return "proHome/proMainSub";
	}
	
	//프로젝트 메인 페이지 미분류 보기
	@GetMapping("proMainG")
	public String proMainPageG(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		model.addAttribute("projectMainList", proService.readProjectNoGroup((String)session.getAttribute("Id")));
		proData(model, request);
		return "proHome/proMainSub";
	}
	
	//프로젝트 그룹 상세 리스트 보기
	@GetMapping("/proGroupD/{groupNo}")
	public String proGroupDetailList(Model model, @PathVariable int groupNo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("Id");
		model.addAttribute("projectMainList", proService.readProjectGroupDetail(groupNo, id));
		proData(model, request);
		return "proHome/proMainSub";
	}
	
	//프로젝트 그룹 생성
	@PostMapping("proGroupC")
	@ResponseBody
	public Map<String, Object> proGroupCreate(HttpServletRequest request, @RequestParam("gN") String groupName) {
		HttpSession session = request.getSession();
		Map <String, Object> map = new HashMap<>();
		String res;
		
		int result = proService.createProjectGroup(groupName, (String)session.getAttribute("Id"));
		System.out.println(result);
		
		if(result > 0) {
			res = "등록";
		} else {
			res = "취소";
		}
		
		map.put("result", res);
		
		return map;
	}
	
	
	//프로젝트 생성 페이지 이동
	@GetMapping("proCreate")
	public String 프로젝트생성페이지(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		model.addAttribute("proVO", new ProVO());//생성 수정 관리, 페이지에서 field값 수정
		model.addAttribute("projectGroupList", proService.readProjectGroup((String)session.getAttribute("Id")));
		return "proHome/proCreate";
	}
	
	//프로젝트 생성
	@PostMapping("proCreate")
	public String 프로젝트생성(ProVO proVO, HttpServletRequest request) {
		HttpSession session = request.getSession();
		//7.09고유키 오류 뜬 적 있음
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("v_pro_name", proVO.getProName());
		param.put("v_pro_content", proVO.getProContent());
		param.put("v_pro_def_tab", proVO.getProDefTab());
		param.put("v_post_write_auth", proVO.getPostWriteAuth());
		param.put("v_post_update_auth", proVO.getPostUpdateAuth());
		param.put("v_post_view_auth", proVO.getPostViewAuth());
		param.put("v_com_write_auth", proVO.getComWriteAuth());
		param.put("v_file_auth", proVO.getFileAuth());
		param.put("v_id", (String)session.getAttribute("Id"));
		
		proService.insertPro(param);
		System.out.println("param 결과 : " + param.toString());
		return "redirect:proMain";
	}
	
	//프로젝트 즐겨찾기 업데이트
	@PostMapping("proUpdate")
	@ResponseBody
	public Map<String, Object> starCheck(@RequestParam("pMN") int pMN, @RequestParam("starC") String starC) {
		String res;
		Map <String, Object> map = new HashMap<>();
		
		if(starC.equals("N")) {
			//프로젝트 즐겨찾기 등록
			proService.updateStarY(pMN);
			res = "등록";
		} else{
			//프로젝트 즐겨찾기 취소
			proService.updateStarN(pMN);
			res = "취소";
		}
		
		map.put("result", res);
		
		return map;
	}
}
