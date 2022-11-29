package org.vasshaug.demicontt.service;

import org.springframework.stereotype.Service;
import org.vasshaug.demicontt.domain.string.RawString;
import org.vasshaug.demicontt.repository.RawStringRepository;

import java.util.List;

@Service
public class RawStringService {

    private final RawStringRepository rawStringRepository;

    public RawStringService(RawStringRepository rawStringRepository) {
        this.rawStringRepository = rawStringRepository;
    }

    public Iterable<RawString> list() {
        return rawStringRepository.findAll();
    }

    public RawString save(RawString rawString) {
        return rawStringRepository.save(rawString);
    }

    public void save(List<RawString> rawStrings) {
        rawStringRepository.saveAll(rawStrings);
    }
}
