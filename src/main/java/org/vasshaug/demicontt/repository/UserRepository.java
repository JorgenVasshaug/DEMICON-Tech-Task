package org.vasshaug.demicontt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vasshaug.demicontt.domain.UserElement;


public interface UserRepository extends JpaRepository<UserElement, Long> {
}
