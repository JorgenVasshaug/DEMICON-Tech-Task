package org.vasshaug.demicontt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vasshaug.demicontt.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
