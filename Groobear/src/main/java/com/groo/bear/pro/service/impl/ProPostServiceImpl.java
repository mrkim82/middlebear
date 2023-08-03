package com.groo.bear.pro.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.files.mapper.FilesMapper;
import com.groo.bear.pro.mapper.ProPostMapper;
import com.groo.bear.pro.service.ProFileVO;
import com.groo.bear.pro.service.ProPostService;
import com.groo.bear.pro.service.postvo.ProDetailSearchVO;
import com.groo.bear.pro.service.postvo.ProInviteMailVO;
import com.groo.bear.pro.service.postvo.ProPartiListVO;
import com.groo.bear.pro.service.postvo.ProPostChartVO;
import com.groo.bear.pro.service.postvo.ProPostCommentVO;
import com.groo.bear.pro.service.postvo.ProPostFeedVO;
import com.groo.bear.pro.service.postvo.ProPostUserVO;
import com.groo.bear.pro.service.postvo.ProPostVO;
import com.groo.bear.pro.service.postvo.ProPostWorkVO;
import com.groo.bear.pro.service.postvo.ProPostWritingVO;
import com.groo.bear.pro.service.postvo.ProWritingUVO;

import lombok.Setter;

@Service
public class ProPostServiceImpl implements ProPostService {
	@Autowired
	ProPostMapper ppm;
	
	@Autowired
	FilesMapper file;
	
	@Setter(onMethod_= @Autowired)
	private ProPostMapper mapper;
	@Setter(onMethod_= @Autowired)
	private FilesMapper attachMapper;

	
	@Override
	public ProPostVO readTopMenu(int proNo, String id) {
		return ppm.readTopMenu(proNo, id);
	}

	@Override
	public int readTopMenuCount(String id, int proNo) {
		return ppm.readTopMenuCount(id, proNo);
	}

	@Override
	public List<ProPostUserVO> readProjectParti(int proNo) {
		return ppm.readProjectParti(proNo);
	}

	@Override
	public void createPostWriting(ProPostWritingVO vo) {
		ppm.createPostWriting(vo);
	}

	@Override
	public void createPostWork(ProPostWorkVO vo) {
		ppm.createPostWork(vo);
	}

	@Override
	public List<ProPostWritingVO> readPostWriting(int postType) {
		return ppm.readPostWriting(postType);
	}

	@Override
	public List<ProPostCommentVO> readPostWritingComment(int proNo) {
		return ppm.readPostWritingComment(proNo);
	}

	@Override
	public int createPostComment(ProPostCommentVO vo) {
		ppm.createPostComment(vo);
		return vo.getComNo();
	}

	@Override
	public int deletePostComment(int comNo) {
		return ppm.deletePostComment(comNo);
	}

	@Override
	public int updatePostComment(ProPostCommentVO vo) {
		return ppm.updatePostComment(vo);
	}

	@Override
	public List<ProPostFeedVO> readFeedPost(int proNo , int postType) {
		return ppm.readFeedPost(proNo, postType);
	}

	@Override
	public int updateWorkPostStatus(ProPostWorkVO vo) {
		return ppm.updateWorkPostStatus(vo);
	}

	@Override
	public List<ProPostChartVO> readPostChart(int proNo) {
		List<ProPostChartVO> list = ppm.readPostChart(proNo);
	    List<ProPostChartVO> updatedList = new ArrayList<>();

	    for (int i = 1; i <= 5; i++) {
	        boolean found = false;
	        for (ProPostChartVO proPostChartVO : list) {
	            if (Integer.parseInt(proPostChartVO.getWorkStatus()) == i) {
	                found = true;
	                updatedList.add(proPostChartVO);
	                break;
	            }
	        }
	        if (!found) {
	            ProPostChartVO emptyVO = new ProPostChartVO();
	            emptyVO.setWorkStatus(String.valueOf(i));
	            emptyVO.setCount(0); // 나머지 값은 0으로 설정
	            updatedList.add(emptyVO);
	        }
	    }
	    return updatedList;
	}

	@Override
	public int updateProfileMemo(ProPostUserVO vo) {
		return ppm.updateProfileMemo(vo);
	}

	@Override
	public int deleteProPost(int proPostNo) {
		return ppm.deleteProPost(proPostNo);
	}

	@Override
	public int updateProWriting(ProWritingUVO vo) {
		int res = 0;
		String title = vo.getPostTitle();
		
		if(title != null) {
			ppm.updateProPostTitle(title, vo.getProPostNo());
			res++;
		}
		
		if(vo.getWritingContent() != null) {
			ppm.updateProWriting(vo);
			res++;
		}
		
		return res;
	}

	@Override
	public List<ProDetailSearchVO> readProInSearch(ProDetailSearchVO vo) {
		return ppm.readProInSearch(vo);
	}

	@Override
	public List<ProPartiListVO> readPartiListM(int proNo) {
		return ppm.readPartiListM(proNo);
	}

	@Override
	public int createInviteMail(List<ProInviteMailVO> vo) {
		int result = 0;
		for (ProInviteMailVO dvo : vo) {
			ppm.createInviteMail(dvo);
			result++;
		}
		return result;
	}

	@Override
	public int deletePro(int proNo) {
		return ppm.deletePro(proNo);   
	}


	@Override
	public int deleteProFile(int proFileNo) {
		int res = file.deleteProFile(proFileNo) + file.deleteProFileMan(proFileNo);
		return res;
	}

	@Override
	public int createProFile(List<ProFileVO> vo) {
		int count = 0;
		//pro_file용 마지막 pro_post_no 번호 조회
		int lastPostNo = ppm.readProPostNo();
		
		for (ProFileVO proFileVO : vo) {
			proFileVO.setProPostNo(lastPostNo);
			
			file.createProFileMan(proFileVO);//pro_file insert
			file.createProFile(proFileVO);//files insert
			count++;
		}
		
		return count;
	}

	@Override
	public List<ProFileVO> getWorkAttach(int proNo) {
		return file.readProFilePost(proNo);
	}

	@Override
	public List<ProFileVO> readProFilePostDetail(int proPostNo) {
		return file.readProFilePostDetail(proPostNo);
	}

	@Override
	public int delteProMemOut(int proNo, String id) {
		return ppm.delteProMemOut(proNo, id);
	}

	@Override
	public int readProDetailCount(int proNo) {
		return ppm.readProDetailCount(proNo);
	}

}
