package com.groo.bear.files.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import groovy.transform.builder.Builder;
import lombok.Data;
@Data
public class FilesVO {
//	FILE_NO          NOT NULL NUMBER(5)    
//	ORIGIN_FILE_NAME NOT NULL VARCHAR2(50) 
//	CHANGE_FILE_NAME NOT NULL VARCHAR2(50) 
//	FILE_SIZE        NOT NULL NUMBER(10)   
//	FILE_DATE        NOT NULL DATE         
//	MAIL_NO                   NUMBER(5)    
//	BOARD_NO                  NUMBER(5)    
//	PRO_FILE_NO               NUMBER(5)
	
	public int fileNo;
	public String originFileName;
	public String changeFileName;
	public BigDecimal fileSize;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date fileDate;
	public int mailNo;
	public int boardNo;
	public int proFileNo;
	
	
	public void setBoardNo(int fileNo) {
		this.fileNo = fileNo;
	}
	
	public FilesVO(Builder builder) {
        this.originFileName = builder.originFileName;
        this.changeFileName = builder.changeFileName;
        this.fileSize = builder.fileSize;
    }

    public FilesVO(String string, String string2, BigDecimal bigDecimal) {
		// TODO Auto-generated constructor stub
	}

	public static class Builder {
        private String originFileName;
        private String changeFileName;
        private BigDecimal fileSize;

        public Builder originFileName(String originFileName) {
            this.originFileName = originFileName;
            return this;
        }

        public Builder changeFileName(String changeFileName) {
            this.changeFileName = changeFileName;
            return this;
        }

        public Builder fileSize(BigDecimal fileSize) {
            this.fileSize = fileSize;
            return this;
        }

        public FilesVO build() {
            return new FilesVO(this);
        }
    }


}
