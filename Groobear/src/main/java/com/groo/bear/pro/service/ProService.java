package com.groo.bear.pro.service;

import java.util.Map;

public interface ProService {
	//프로젝트 생성
	//public int createProject(ProVO proVO);
	public void insertPro(Map<String, Object> map);
}
