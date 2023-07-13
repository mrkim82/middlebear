package com.groo.bear;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class EncriptTest { 
	@Test
	public void test() {
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder(); 
		String password = scpwd.encode("honghong@@"); 
		System.out.println(password);
	}
}