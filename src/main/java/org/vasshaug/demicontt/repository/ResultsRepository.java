package org.vasshaug.demicontt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vasshaug.demicontt.domain.ResultElement;

public interface ResultsRepository extends JpaRepository<ResultElement, Long> {
}
