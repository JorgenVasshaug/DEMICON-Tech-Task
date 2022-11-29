package com.github.jorgenvasshaug.springreactRU.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.github.jorgenvasshaug.springreactRU.domain.entity.randomuser.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
