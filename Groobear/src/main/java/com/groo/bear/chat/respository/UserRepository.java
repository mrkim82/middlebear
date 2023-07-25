package com.groo.bear.chat.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groo.bear.chat.domain.Users;

public interface UserRepository extends JpaRepository<Users, String> {
}
