package com.groo.bear.chat.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groo.bear.chat.domain.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
	
}
