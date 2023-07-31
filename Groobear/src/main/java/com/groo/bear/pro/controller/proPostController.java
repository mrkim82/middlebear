package com.groo.bear.pro.controller;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import com.groo.bear.comm.DateUtil;
import com.groo.bear.files.domain.FilesVO;
import com.groo.bear.pro.service.ProFileVO;
import com.groo.bear.pro.service.ProPostSchService;
import com.groo.bear.pro.service.ProPostService;
import com.groo.bear.pro.service.ProPostTaskService;
import com.groo.bear.pro.service.ProService;
import com.groo.bear.pro.service.ProTodoNVoteService;
import com.groo.bear.pro.service.PublicCodeService;
import com.groo.bear.pro.service.postvo.ProDetailSearchVO;
import com.groo.bear.pro.service.postvo.ProInviteMailVO;
import com.groo.bear.pro.service.postvo.ProPostChartVO;
import com.groo.bear.pro.service.postvo.ProPostCommentVO;
import com.groo.bear.pro.service.postvo.ProPostUserVO;
import com.groo.bear.pro.service.postvo.ProPostVO;
import com.groo.bear.pro.service.postvo.ProPostWorkVO;
import com.groo.bear.pro.service.postvo.ProPostWritingVO;
import com.groo.bear.pro.service.postvo.ProWritingUVO;
import com.groo.bear.pro.service.task.ProWorkViewVO;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
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
		model.addAttribute("readPartiListM", proPostService.readPartiListM(proNo));//프로젝트 초대 할 인원
		
		//System.out.println("게시글"+model.getAttribute("projectPartiMember"));
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
			model.addAttribute("readFeedPost", proPostService.readFeedPost(proNo , vo.getPostType()));
			model.addAttribute("readSchparti", proPostSchService.readSchparti(id));
			model.addAttribute("readPartiList", Sch.readPartiList(proNo));
			model.addAttribute("readPartiZone", Sch.readPartiZone(proNo));
			//업무
			model.addAttribute("readTaskWorkPerson", taskS.readTaskWorkPerson(proNo));//업무 담당자 조회
			//할 일
			model.addAttribute("readTodoList", todoNVote.readTodoList(proNo));//할 일 조회
			model.addAttribute("readAllTodoListPer", todoNVote.readAllTodoListPer(proNo));//할 일 퍼센트 조회
			
			//투표
			model.addAttribute("readVoteList", todoNVote.readVoteList(proNo));//투표 조회
			model.addAttribute("readVoteListCheck", todoNVote.readVoteListCheck(proNo));//투표 내용
			model.addAttribute("readVoteListParti", todoNVote.readVoteListParti(proNo));//투표 인원
			model.addAttribute("readVotePartiCount", todoNVote.readVotePartiCount(proNo));//투표 인원 수
			model.addAttribute("readxxVote", todoNVote.readxxVote(id, proNo));//투표 checked를 위한 조회
			
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
	
	//프로필 메모 변경
	@PutMapping("updateProfileMemo")
	@ResponseBody
	public Map<String, Object> updateProfileMemo(HttpServletRequest request, @RequestBody ProPostUserVO vo) {
		int result = proPostService.updateProfileMemo(vo);
		
		return Collections.singletonMap("result", result > 0 ? "성공" : "취소");
	}
	
	@PostMapping("creVote")
	public List<ProPostChartVO> createPostVote(@RequestBody int proNo) {
	    // 프로젝트 번호를 기반으로 데이터 조회
	    return proPostService.readPostChart(proNo);
	}
	
	//댓글 삭제
	@PostMapping("delProPost")
	@ResponseBody
	public Map<String, Object> deleteProPost(HttpServletRequest request, @RequestBody int delProPostNo) {
		int result = proPostService.deleteProPost(delProPostNo);
		return Collections.singletonMap("result", result > 0 ? "성공" : "취소");
	}
	
	//글 수정
	@PutMapping("upProPost")
	@ResponseBody
	public Map<String, Object> updateProPost(@RequestBody ProWritingUVO vo) {
		int result = proPostService.updateProWriting(vo);
		return Collections.singletonMap("result", result);
	}
	
	// 프로젝트 내 검색
	@GetMapping("proSearch/{proNo}")
	public String 프로젝트내검색(Model model, HttpServletRequest request, @PathVariable int proNo, ProDetailSearchVO vo) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("Id");
		proData2(model, request);
		
		model.addAttribute("projectTopBar", proPostService.readTopMenu(proNo, id));//메뉴 상단바 조회
		model.addAttribute("readPublicCodeColorAll", publicC.readPublicCodeColorAll());//공통 색상 전체
		model.addAttribute("readProAuth", proService.readProAuth(proNo));//권한 및 프로젝트 마스터 조회
		
		model.addAttribute("readProInSearch", proPostService.readProInSearch(vo));//검색 내용
		
		return "proPost/proPostSearch";
	}
	
	//초대 메일 발송
	@PostMapping("inviteMail")
	@ResponseBody
	public Map<String, Object> inviteMail(HttpServletRequest request, @RequestBody List<ProInviteMailVO> vo) {
		int result = proPostService.createInviteMail(vo);
		return Collections.singletonMap("result", result);
	}
	
	//프로젝트 삭제
	@DeleteMapping("delPro")
	@ResponseBody
	public Map<String, Object> deletePro(@RequestBody int proNo) {
		int result = proPostService.deletePro(proNo);
		return Collections.singletonMap("result", result > 0 ? "성공" : "취소");
	}
	
	//파일 등록
	@PostMapping("proFileInsert")
	@ResponseBody
	public Map<String, Object> 파일추가(@RequestBody List<ProFileVO> vo) {
		int result = proPostService.createProFile(vo);
		
		return Collections.singletonMap("result", result);
	}
	
	//업무 파일조회
	@GetMapping(value ="/getWorkAttach", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProFileVO>> 업무파일조회(@RequestParam int proNo) {
		log.info("getAttachList" + proNo);
		return new ResponseEntity<>(proPostService.getWorkAttach(proNo), HttpStatus.OK);
	}
	
	//파일상세조회
	@GetMapping(value ="/getWorkAttachDetail", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProFileVO>> 파일상세조회(@RequestParam int proPostNo) {
		log.info("getAttachList" + proPostNo);
		return new ResponseEntity<>(proPostService.readProFilePostDetail(proPostNo), HttpStatus.OK);
	}
	
	//파일 삭제
	@DeleteMapping("delProFile")
	@ResponseBody
	public Map<String, Object> delProFile(@RequestBody int proFileNo) {
		int result = proPostService.deleteProFile(proFileNo);
		System.out.println(proFileNo);
		System.out.println(result);
		return Collections.singletonMap("result", result/2 > 0 ? "성공" : "취소");
	}
	
	
}