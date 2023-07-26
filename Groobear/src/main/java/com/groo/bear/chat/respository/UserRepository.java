package com.groo.bear.chat.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groo.bear.chat.domain.Users;

public interface UserRepository extends JpaRepository<Users, String> {
	//JpaRepository는 기본 CRUD가 가능하도록 인터페이스를 제공해준다.
}
