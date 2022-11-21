package org.vasshaug.demicontt.service;

import org.springframework.stereotype.Service;
import org.vasshaug.demicontt.repository.ResultsRepository;
import org.vasshaug.demicontt.domain.Result;
import java.util.List;

@Service
public class ResultsService {

    private final ResultsRepository resultsRepository;

    public ResultsService(ResultsRepository resultsRepository) {
        this.resultsRepository = resultsRepository;
    }

    public Iterable<Result> list() {
        return resultsRepository.findAll();
    }

    public Result save(Result result) {
        return resultsRepository.save(result);
    }

    public void save(List<Result> results) {
        resultsRepository.saveAll(results);
    }
}
