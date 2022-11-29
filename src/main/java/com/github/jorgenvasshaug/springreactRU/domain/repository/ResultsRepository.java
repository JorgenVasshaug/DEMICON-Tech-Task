package com.github.jorgenvasshaug.springreactRU.domain.repository;

import com.github.jorgenvasshaug.springreactRU.domain.entity.randomuser.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultsRepository extends JpaRepository<Result, Long> {
}
