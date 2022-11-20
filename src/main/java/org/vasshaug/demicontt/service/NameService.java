package org.vasshaug.demicontt.service;

import org.springframework.stereotype.Service;
import org.vasshaug.demicontt.dao.NameRepository;
import org.vasshaug.demicontt.entity.Name;

import java.util.List;

@Service
public class NameService {

    private final NameRepository nameRepository;

    public NameService(NameRepository nameRepository) {
        this.nameRepository = nameRepository;
    }

    public Iterable<Name> list() {
        return nameRepository.findAll();
    }

    public Name save(Name name) {
        return nameRepository.save(name);
    }

    public void save(List<Name> names) {
        nameRepository.saveAll(names);
    }
}
