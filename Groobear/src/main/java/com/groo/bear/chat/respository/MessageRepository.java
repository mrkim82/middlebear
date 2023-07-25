package com.groo.bear.chat.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groo.bear.chat.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
	
}
