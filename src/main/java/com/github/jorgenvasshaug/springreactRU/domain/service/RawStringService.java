package com.github.jorgenvasshaug.springreactRU.domain.service;

import org.springframework.stereotype.Service;
import com.github.jorgenvasshaug.springreactRU.domain.entity.string.RawString;
import com.github.jorgenvasshaug.springreactRU.domain.repository.RawStringRepository;

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
