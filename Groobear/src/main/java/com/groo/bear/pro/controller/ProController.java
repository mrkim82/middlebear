package com.groo.bear.pro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.groo.bear.pro.service.ProGroupVO;
import com.groo.bear.pro.service.ProService;
import com.groo.bear.pro.service.ProUsersVO;
import com.groo.bear.pro.service.ProVO;

@Controller
public class ProController {
	
	@Autowired
	ProService proService;
	
	//공통 데이터(사이드바) 전달
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
		Map<String, Object> param = new HashMap<String, Object>();
		
		String id = (String)session.getAttribute("Id");
		ProUsersVO vo = proService.readOrder(id);
		
		param.put("id", id);
		param.put("proPartiFilter", vo.getProPartiFilter());//기본값 참여중or관리자 프로젝트 구분
		param.put("proRange", vo.getProRange());//정렬 기본값
		System.out.println(param);
		model.addAttribute("projectMainList", proService.readProject(param));
		model.addAttribute("userProjectFilter", vo);
		System.out.println(model);
		proData(model, request);
		return "proHome/proMain";
	};
	
	//프로젝트 메인 페이지 카테고리 및 정렬
	@PostMapping("proMainO")
	@ResponseBody
	public Map<String, Object> proMainPageOrder(Model model, HttpServletRequest request, @RequestBody ProUsersVO vo) {
		HttpSession session = request.getSession();
		Map<String, Object> param = new HashMap<String, Object>();
		Map <String, Object> map = new HashMap<>();
		String res;
		String id = (String)session.getAttribute("Id");
		String newProPartiFilter = vo.getProPartiFilter();//필터 신규값
		String newProRange = vo.getProRange();//신규 정렬값
		ProUsersVO vo2 = proService.readOrder(id);//기본 데이터 저장 값
		String oldProPartiFilter = vo2.getProPartiFilter();
		String oldProRange = vo2.getProRange();
		int result = 0;
		//System.out.println("필터 : " + newProPartiFilter);
		//System.out.println("정렬 : " + newProRange);
		
		param.put("id", id);
		
		//oldProPartiFilter = oldProPartiFilter == null ? "" : oldProPartiFilter;
		
		//정렬 변경시
		if(newProPartiFilter == null) {
			param.put("proRange", newProRange);//정렬 기본값
			param.put("proPartiFilter", oldProPartiFilter);
			result = proService.updateProjectOrder(newProRange, id);
			//System.out.println("정렬임");
		//필터 변경시
		} else if (newProRange == null || newProRange == "") {
			//newProPartiFilter = newProPartiFilter != "" ? newProPartiFilter : "";
			//System.out.println(newProPartiFilter);
			//System.out.println("필터임");
			param.put("proRange", oldProRange);//정렬 기본값
			param.put("proPartiFilter", newProPartiFilter);//기본값 참여중or관리자 프로젝트 구분
			result = proService.updateProjectFilter(newProPartiFilter, id);
		} else {
			System.out.println("오류다!!!");
		}
		
		model.addAttribute("projectMainList", proService.readProject(param));
		model.addAttribute("userProjectFilter", vo2);
		proData(model, request);
		
		if(result > 0) {
			res = "성공";
			
		} else {
			res = "취소";
		}
		
		map.put("result", res);
		return map;
	}
	
	//프로젝트 메인 페이지 즐찾 보기
	@GetMapping("proMainS")
	public String proMainPageS(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		model.addAttribute("projectMainList", proService.readProjectStar((String)session.getAttribute("Id")));
		proData(model, request);
		return "proHome/proMainStar";
	}
	
	//프로젝트 메인 페이지 숨김 보기
	@GetMapping("proMainH")
	public String proMainPageH(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		model.addAttribute("projectMainList", proService.readProjectHide((String)session.getAttribute("Id")));
		proData(model, request);
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
		System.out.println(model);
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
		int proGroupNo = proService.readProjectGroupNo();
		
		if(result > 0) {
			res = "성공";
			
		} else {
			res = "취소";
		}
		
		map.put("result", res);
		map.put("pGN", proGroupNo);
		System.out.println(map);
		return map;
	}
	
	//프로젝트 그룹 수정
	@PutMapping("proGroupNameC")
	@ResponseBody
	public Map<String, Object> proGroupUpdate(@RequestBody ProGroupVO vo) {
		Map <String, Object> map = new HashMap<>();
		String res;
		
		int result = proService.updateGroupName(vo);
		
		if(result > 0) {
			res = "성공";
			
		} else {
			res = "취소";
		}
		
		map.put("result", res);
		System.out.println(vo);
		
		return map;
	}
	
	//프로젝트 그룹 삭제
	@DeleteMapping("FolderGroupD/{groupNo}")
	@ResponseBody
	public Map<String, Object> proGroupDelete(@PathVariable int groupNo) {
		Map <String, Object> map = new HashMap<>();
		String res;
		
		int result = proService.deleteGroup(groupNo);
		
		if(result > 0) {
			res = "성공";
			
		} else {
			res = "취소";
		}
		
		map.put("result", res);
		System.out.println(map);
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
