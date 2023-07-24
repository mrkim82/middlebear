package com.groo.bear.task;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.groo.bear.files.domain.FilesVO;
import com.groo.bear.files.mapper.FilesMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class FileCheckTask {

//	@Scheduled(cron="0 * * * * *")
//	public void checkFiles()throws Exception{
//		log.warn("File Check Task run. . . . . . . . . . . . . .");
//		log.warn("==============================================");
//	}
	
	@Setter(onMethod_ = {@Autowired })
	private FilesMapper attachMapper;
	
	private String getFolderYesterDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		
		cal.add(Calendar.DATE, -1);
		
		String str = sdf.format(cal.getTime());
		
		return str.replace("-", File.separator);
	}
	//바로 테스트 하기 위함.
//	@PostConstruct
//	@Scheduled(fixedDelayString = "10000")
//	public void checkFiles() throws Exception {
//		log.warn("File Check Task run.............................");
//		log.warn(new Date());
//		
//		//file list in database
//		List<FilesVO> fileList = attachMapper.getOldFiles();
//		
//		//ready for check file in directory with database file list
//		List<Path> fileListPaths = fileList.stream()
//				.map(vo-> Paths.get("C:\\upload", vo.getUploadPath(),vo.getUuid() + "_" + vo.getFileName()))
//				.collect(Collectors.toList());
//				
//		//image file has thumnail file
//		fileList.stream().filter(vo -> vo.isFileType() == true)
//			.map(vo -> Paths.get("C:\\upload", vo.getUploadPath(),
//					vo.getUuid() + "_" + vo.getFileName()))
//			.forEach(p -> fileListPaths.add(p));
//		
//		log.warn("=================================================");
//		
//		fileListPaths.forEach(p -> log.warn(p));
//		
//		//files in yesterday directory
//		String currentDay = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
//	    File targetDir = Paths.get("C:\\upload", currentDay).toFile();
//
//	    File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false);
//	    
//		
//		log.warn("----------------------------------------------------");
//		for(File file : removeFiles) {
//			log.warn(file.getAbsolutePath());
//			file.delete();
//		}
//	}
	
	@Scheduled(cron = "0 0 2 * * *")
	public void checkFiles() throws Exception {
		log.warn("File Check Task run.............................");
		log.warn(new Date());
		
		//file list in database
		List<FilesVO> fileList = attachMapper.getOldFiles();
		
		//ready for check file in directory with database file list
		List<Path> fileListPaths = fileList.stream()
				.map(vo-> Paths.get("C:\\upload", vo.getUploadPath(),vo.getUuid() + "_" + vo.getFileName()))
				.collect(Collectors.toList());
				
		//image file has thumnail file
		fileList.stream().filter(vo -> vo.isFileType() == true)
			.map(vo -> Paths.get("C:\\upload", vo.getUploadPath(),
					vo.getUuid() + "_" + vo.getFileName()))
			.forEach(p -> fileListPaths.add(p));
		
		log.warn("=================================================");
		
		fileListPaths.forEach(p -> log.warn(p));
		
		//files in yesterday directory
		File targetDir = Paths.get("C:\\upload", getFolderYesterDay()).toFile();
		
		File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false);
		
		log.warn("----------------------------------------------------");
		for(File file : removeFiles) {
			log.warn(file.getAbsolutePath());
			file.delete();
		}
	}
}

