package com.groo.bear.pro.controller;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.groo.bear.comm.DateUtil;
import com.groo.bear.pro.service.ProPostSchService;
import com.groo.bear.pro.service.ProPostService;
import com.groo.bear.pro.service.ProPostTaskService;
import com.groo.bear.pro.service.ProService;
import com.groo.bear.pro.service.ProTodoNVoteService;
import com.groo.bear.pro.service.PublicCodeService;
import com.groo.bear.pro.service.postvo.ProPostChartVO;
import com.groo.bear.pro.service.postvo.ProPostCommentVO;
import com.groo.bear.pro.service.postvo.ProPostVO;
import com.groo.bear.pro.service.postvo.ProPostWorkVO;
import com.groo.bear.pro.service.postvo.ProPostWritingVO;
import com.groo.bear.pro.service.task.ProWorkViewVO;

@Controller
public class proPostController {

	@Autowired
	ProService proService;
	
	@Autowired
	ProPostService proPostService;
	
	@Autowired
	ProPostSchService proPostSchService;
	
	@Autowired
	ProPostSchService Sch;//스케쥴
	
	@Autowired
	PublicCodeService publicC;//공통 코드
	
	@Autowired
	ProTodoNVoteService todoNVote;//할 일, 투표
	
	@Autowired
	ProPostTaskService taskS;//프로젝트(업무)
	
	//공통 데이터(사이드바) 전달
	private Model proData2(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		model.addAttribute("projectGroupList", proService.readProjectGroup((String)session.getAttribute("Id")));
		model.addAttribute("projectPartiList", proService.readProjectParti((String)session.getAttribute("Id")));//프로젝트 참가자 수
		
		return model;
	}
	
	@GetMapping("proPostMain/{proNo}")
	public String proPostPage(Model model, HttpServletRequest request, @PathVariable int proNo, ProPostVO vo) {
		HttpSession session = request.getSession();
		String pagePath ="";
		int homeTab = Integer.parseInt(vo.getHomeTab());
		String id = (String)session.getAttribute("Id");
		proData2(model, request);
		
		model.addAttribute("projectTopBar", proPostService.readTopMenu(proNo, id));//메뉴 상단바 조회
		model.addAttribute("projectUserCount", proPostService.readTopMenuCount(id, proNo));//지울예정
		model.addAttribute("projectPartiMember", proPostService.readProjectParti(proNo));//해당 프로젝트 회원 정보 전체 조회
		model.addAttribute("readWorkGroup", taskS.readWorkGroup(proNo));//업무 그룹 조회
		model.addAttribute("projectWritingDetaisComment", proPostService.readPostWritingComment(proNo));//댓글
		model.addAttribute("readPublicCodeColorAll", publicC.readPublicCodeColorAll());//공통 색상 전체
		model.addAttribute("cTime" , new Date());//현재시간
		model.addAttribute("beforeOneDay" , DateUtil.beforeOneDay());//하루전
		model.addAttribute("afterOneDay" , DateUtil.afterOneDay());//하루뒤
		model.addAttribute("readProAuth", proService.readProAuth(proNo));//권한 및 프로젝트 마스터 조회
		
		System.out.println("게시글"+model.getAttribute("projectPartiMember"));
		switch (homeTab) {
		//업무
		case 1 :
			model.addAttribute("readTaskAllList", taskS.readTaskAllList(proNo));//업무 전체 조회
			model.addAttribute("readTaskWorkPerson", taskS.readTaskWorkPerson(proNo));//업무 담당자 조회
			model.addAttribute("readWorkDetail", taskS.readWorkDetail(proNo));//업무 단건 조회
			model.addAttribute("readWorkView", taskS.readWorkView(proNo, id));//멤버 업무 조회 설정
			
			//System.out.println("게시글"+model.getAttribute("readWorkView"));
			pagePath = "proPost/proPostTask";
			break;
		//피드
		case 2 :
			//글 조회(임시)
			model.addAttribute("readFeedPost", proPostService.readFeedPost(proNo));
			model.addAttribute("readSchparti", proPostSchService.readSchparti(id));
			model.addAttribute("readPartiList", Sch.readPartiList(proNo));
			model.addAttribute("readPartiZone", Sch.readPartiZone(proNo));
			//할 일
			model.addAttribute("readTodoList", todoNVote.readTodoList(proNo));//할 일 조회
			model.addAttribute("readAllTodoListPer", todoNVote.readAllTodoListPer(proNo));//할 일 퍼센트 조회
			
			//투표
			model.addAttribute("readVoteList", todoNVote.readVoteList(proNo));//투표 조회
			model.addAttribute("readVoteListCheck", todoNVote.readVoteListCheck(proNo));//투표 내용
			model.addAttribute("readVoteListParti", todoNVote.readVoteListParti(proNo));//투표 인원
			
			//System.out.println("게시글"+model.getAttribute("readFeedPost"));
			pagePath = "proPost/proPostDetail";
			break;
		//간트차트
		case 3 :
			
			pagePath = "proPost/proPostChart";
			break;
		//캘린더
		case 4 :
			model.addAttribute("readWorkSchView", Sch.readWorkSchView(proNo));//업무, 일정 조회(바)
			model.addAttribute("readWorkDetail", taskS.readWorkDetail(proNo));//업무 단건 조회
			
			model.addAttribute("readCalDetail", Sch.readCalDetail(proNo));//일정 단건
			model.addAttribute("readPartiList", Sch.readPartiList(proNo));//참석자 조회
			model.addAttribute("readPartiZone", Sch.readPartiZone(proNo));
			model.addAttribute("readSchparti", proPostSchService.readSchparti(id));
			//System.out.println("게시글"+model.getAttribute("readWorkSchView"));
			pagePath = "proPost/proPostSchd";
			break;
		//파일
		case 5 :
			
			pagePath = "proPost/proPostFile";
			break;
		//알림
		case 6 :
			
			pagePath = "proPost/proPostAlarm";
			break;
		//에러
		default:
			pagePath = "proPost/proPostError";
			break;
		}
		
		return pagePath;
	}
	
	
	
	//글 생성
	@PostMapping("postCreateWriting")
	@ResponseBody
	public Map<String, Object> postCreateWriting(HttpServletRequest request, @RequestBody ProPostWritingVO vo) {
		HttpSession session = request.getSession();
		Map <String, Object> map = new HashMap<>();
		String res = "";
		vo.setId((String)session.getAttribute("Id"));
		
		proPostService.createPostWriting(vo);
		
		map.put("result", res);
		return map;
	}
	
	//업무 생성
	@PostMapping("postCreateWork")
	@ResponseBody
	public Map<String, Object> postCreateWork(HttpServletRequest request, @RequestBody ProPostWorkVO vo) {
		HttpSession session = request.getSession();
		Map <String, Object> map = new HashMap<>();
		String res = "";
		if(vo.getWorkPersonArr().length == 0) {
			vo.setWorkPersonArr(null);
		}
		vo.setId((String)session.getAttribute("Id"));
		
		proPostService.createPostWork(vo);
		
		map.put("result", res);
		return map;
	}
	
	//댓글 작성
	@PostMapping("postCreateComment")
	@ResponseBody
	public Map<String, Object> createPostComment(HttpServletRequest request, @RequestBody ProPostCommentVO vo) {
		HttpSession session = request.getSession();
		vo.setId((String)session.getAttribute("Id"));
		
		int comNo = proPostService.createPostComment(vo);
		return Collections.singletonMap("result", comNo);
	}
	
	//댓글 수정
	@PutMapping("postUpdateComment")
	@ResponseBody
	public Map<String, Object> proGroupUpdate(@RequestBody ProPostCommentVO vo) {
		int result = proPostService.updatePostComment(vo);
		return Collections.singletonMap("result", result > 0 ? "성공" : "취소");
	}
	
	//댓글 삭제
	@PostMapping("postDeleteComment")
	@ResponseBody
	public Map<String, Object> deletePostComment(HttpServletRequest request, @RequestBody ProPostCommentVO vo) {
		int result = proPostService.deletePostComment(vo.getComNo());
		return Collections.singletonMap("result", result > 0 ? "성공" : "취소");
	}
	
	//업무 상태 수정
	@PutMapping("updateWorkPostStatus")
	@ResponseBody
	public Map<String, Object> updateWorkPostStatus(@RequestBody ProPostWorkVO vo) {
		int result = proPostService.updateWorkPostStatus(vo);
		return Collections.singletonMap("result", result > 0 ? "성공" : "취소");
	}
	
	//차트 조회
	@PostMapping("googleChart")
	@ResponseBody
	public List<ProPostChartVO> readGoogleChart(@RequestBody int proNo) {
	    // 프로젝트 번호를 기반으로 데이터 조회
	    return proPostService.readPostChart(proNo);
	}
	
	//멤버별 업무 조회 변경
	@PutMapping("updateWorkView")
	@ResponseBody
	private Map<String, Object> updateWorkView(HttpServletRequest request, @RequestBody ProWorkViewVO vo) {
		HttpSession session = request.getSession();
		vo.setId((String)session.getAttribute("Id"));
		
		int result = taskS.updateWorkView(vo);
		return Collections.singletonMap("result", result > 0 ? "성공" : "취소");
	}
}
