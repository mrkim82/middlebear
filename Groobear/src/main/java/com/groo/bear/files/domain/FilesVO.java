package com.groo.bear.files.domain;

import lombok.Data;

@Data
public class FilesVO {
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;
	
	private int boardNo;
}
