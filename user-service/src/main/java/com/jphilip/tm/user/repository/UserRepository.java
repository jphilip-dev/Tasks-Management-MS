package com.jphilip.tm.user.repository;

import com.jphilip.tm.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
