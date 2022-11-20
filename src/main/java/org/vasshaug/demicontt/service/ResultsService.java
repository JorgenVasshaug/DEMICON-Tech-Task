package org.vasshaug.demicontt.service;

import org.springframework.stereotype.Service;
import org.vasshaug.demicontt.repository.ResultsRepository;
import org.vasshaug.demicontt.domain.ResultElement;


import java.util.List;

@Service
public class ResultsService {

    private final ResultsRepository resultsRepository;

    public ResultsService(ResultsRepository resultsRepository) {
        this.resultsRepository = resultsRepository;
    }

    public Iterable<ResultElement> list() {
        return resultsRepository.findAll();
    }

    public ResultElement save(ResultElement result) {
        return resultsRepository.save(result);
    }

    public void save(List<ResultElement> results) {
        resultsRepository.saveAll(results);
    }
}
