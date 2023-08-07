package com.groo.bear.pro.service.postvo;

import lombok.Data;

@Data
public class ProUserImgVO {
	private String uuid;
	private String uploadPath;
	private String fileName;
	private int comNo;
	
	public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
