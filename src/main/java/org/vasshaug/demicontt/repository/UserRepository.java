package org.vasshaug.demicontt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vasshaug.demicontt.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
