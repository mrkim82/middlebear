package com.groo.bear.paging;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
public class Criteria {

	private int page; //현재페이지번호
	private int perPageNum; //페이지당 게시물 개수
	private String keyword;
	private String type;
	
	public int getPageNum() {
		return(this.page-1)*perPageNum;
	}
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
		}else {
			this.page = page;
		}
	}

	public void setPerPageNum(int perPageNum) {
		int cnt = this.perPageNum;
		if(perPageNum != cnt) {
			this.perPageNum = perPageNum;
		}else {
			this.perPageNum = cnt;
		}
	}
		

	public String getListLink() {
		Paging paging = new Paging();
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.perPageNum)
				.queryParam("amount", paging.getTotalCount())
				.queryParam("type", this.getType())
				.queryParam("keyword", this.getKeyword());
		
		return builder.toUriString();
	}
}
