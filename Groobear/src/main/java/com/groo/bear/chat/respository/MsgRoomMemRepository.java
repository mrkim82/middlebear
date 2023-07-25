package com.groo.bear.chat.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groo.bear.chat.domain.MsgRoomMem;

public interface MsgRoomMemRepository extends JpaRepository<MsgRoomMem, Integer> {
	
}