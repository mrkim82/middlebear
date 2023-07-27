package com.groo.bear.pro.service.todovote;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProPostTodoCreVO {
	private String postTitle;
	private List<ProPostTodoCreListVO> pptcl;
	
	private int proNo;
	private String id;
	private String proPostNo;
	
}
