package org.vasshaug.demicontt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vasshaug.demicontt.json.ResultElement;

public interface ResultsRepository extends JpaRepository<ResultElement, Long> {
}
