package com.groo.bear.pro.service.postvo;

//import java.util.Date;

//import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ProPostCommentVO {
	private int comNo;
	private String comContent;
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm")
	private String comDate;
	private String name;
	private int proPostNo;
	private String profileImg;
	private String id;
}
