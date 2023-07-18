package com.groo.bear.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.groo.bear.com.service.ComService;

@RestController
public class ComController {
	
	@Autowired
	ComService comService;
	
//	@GetMapping("/comList")
//	public 
	
}
