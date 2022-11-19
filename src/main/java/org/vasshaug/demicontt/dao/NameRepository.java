package org.vasshaug.demicontt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vasshaug.demicontt.entity.Name;

public interface NameRepository extends JpaRepository<Name, Long> {
}
