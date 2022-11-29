package com.github.jorgenvasshaug.springreactRU.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.github.jorgenvasshaug.springreactRU.domain.entity.string.RawString;

public interface RawStringRepository extends JpaRepository<RawString, Long> {
}
