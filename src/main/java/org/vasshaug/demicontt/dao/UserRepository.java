package org.vasshaug.demicontt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vasshaug.demicontt.json.UserElement;


public interface UserRepository extends JpaRepository<UserElement, Long> {
}
